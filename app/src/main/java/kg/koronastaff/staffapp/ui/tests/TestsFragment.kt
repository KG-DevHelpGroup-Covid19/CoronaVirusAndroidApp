package kg.koronastaff.staffapp.ui.tests

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.database.Base
import kg.koronastaff.staffapp.database.Cache
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.models.PollChoice
import kg.koronastaff.staffapp.models.TestQuestion
import kg.koronastaff.staffapp.models.TestResults
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_tests.*
import kotlinx.android.synthetic.main.fragment_tests.view.*
import kotlin.collections.ArrayList

class TestsFragment : Fragment() {
    private var questionCount = 0
    private var mViewModel: TestsViewModel? = null
    private var sendEnabled = false
    private lateinit var base: Base
    private lateinit var token: String
    private lateinit var cities: ArrayList<City>
    private lateinit var cache: Cache

    private var questions: ArrayList<TestQuestion> = arrayListOf()

    private lateinit var testRegion: Spinner
    private lateinit var currentCities: ArrayList<City>

    private var testResults: TestResults = TestResults("", "","","", "", 1, 0)

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        root = inflater.inflate(R.layout.fragment_tests, container, false)
        base = Base(activity!!)
        testRegion = root.test_region
        cache = Cache(activity!!)

        root.back_button.setOnClickListener {
            activity!!.onBackPressed()
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)
        cities = cache.getCities()

        mViewModel!!.getQuestions()?.subscribe{
            updateQuestions(it.results)
            updateQuestion(root)
            root.progressBar2.visibility = View.GONE
        }

        if (cities.size < 1){
            mViewModel!!.getCities()?.subscribe{

                cache.saveCities(it.results)
                cities = it.results
                mViewModel!!.parseCities(it.results)
                updateSpinnerRegions(mViewModel!!.parsedRegions)
                updateSpinnerCities(mViewModel!!.parsedCities[mViewModel!!.parsedRegions[0]])
            }
        }else{
            mViewModel!!.parseCities(cities)
            updateSpinnerRegions(mViewModel!!.parsedRegions)
            updateSpinnerCities(mViewModel!!.parsedCities[mViewModel!!.parsedRegions[0]])
        }

        test_region.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateSpinnerCities(mViewModel!!.parsedCities[mViewModel!!.parsedRegions[position]])
                testResults.region = mViewModel!!.parsedRegions[position]
            }
        }

        test_district.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                testResults.city = currentCities[position].name
            }
        }

        test_gender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                testResults.sex = position + 1
            }
        }

        updateToken()
    }

    private fun updateQuestions(list: ArrayList<TestQuestion>){
        questions = list
        questions.add(TestQuestion(0, getString(R.string.test_positive),
                getString(R.string.test_positive_second),
                arrayListOf(TestQuestion.Choice(0, getString(R.string.test_profile_send), true)), true))
    }

    private fun createButtons(list: ArrayList<TestQuestion.Choice>, v: View){
        var i = 0
        val ll: LinearLayout = buttons_container
        ll.removeAllViews()
        list.forEach{choice ->
            val button = Button(activity!!)
            button.apply {
                val m = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                m.topMargin = 30
                layoutParams = m
                textSize = 13f
                text = choice.sc_value
                visibility = View.VISIBLE
            }
            button.setBackgroundResource(R.drawable.button_bordered_background)
            button.setOnClickListener {
                choose(choice, it)
            }
            ll.addView(button, buttons_container.childCount)
            i++
        }
    }

    @SuppressLint("ShowToast")
    private fun choose(choice: TestQuestion.Choice, v: View){
        if (choice.isSend){
                var age = test_age.text.toString()
                if (age.isEmpty()) age = "0"
                testResults.first_name = test_name.text.toString()
                testResults.last_name = test_surname.text.toString()
                testResults.age = Integer.parseInt(age)
                testResults.district = "a"
                mViewModel!!.postTestResult(testResults, base.getToken()!!)?.subscribe({
                    Toast.makeText(activity, getString(R.string.toast_results_send), Toast.LENGTH_LONG).show()
                    send(v)}, {
                    Toast.makeText(activity, getString(R.string.toast_error), Toast.LENGTH_LONG).show()
                    it.printStackTrace()
                })
        }else{
            val res = PollChoice(
                    base.getToken()!!,
                    questions[questionCount - 1].id,
                    choice.id
            )
            mViewModel!!.postPollChoice(res)?.subscribe({
                Toast.makeText(activity, getString(R.string.toast_test_ok), Toast.LENGTH_LONG).show()
            }, {
                base.addPollChoice(res)
                it.printStackTrace()
            })
            updateQuestion(v)
        }
    }

    private fun updateQuestion(v: View){
        val q = questions[questionCount]
        question.text = q.title
        test_additional_text.text = q.description
        createButtons(q.choices, v)
        if(q.isLast){loadTestingAnswer(v)}
        counterUpdate(v)
        println(questions)
        questionCount++
    }

    @SuppressLint("SetTextI18n")
    private fun counterUpdate(v: View) {
        textView3.text =
                (questionCount + 1).toString() + " "+
                        getString(R.string.of) + " " + (questions.size)
    }

    private fun loadTestingAnswer(v: View) {
        test_form.visibility = View.VISIBLE
        sendEnabled = true
    }

    private fun send(v: View) {
        Navigation.findNavController(v).navigate(R.id.menu_quations_covid19)
        hideKeyboardFrom(activity, v)
    }

    private fun updateToken(){
        token = base.getToken()!!
        if (token.isEmpty()){
            mViewModel!!.getToken().subscribe(
                    {
                        base.saveToken(it.unique_id)
                    }, {
                        it.printStackTrace()
                    }
            )
        }
    }

    fun updateSpinnerCities(list: java.util.ArrayList<City>?){
        val listNew = arrayListOf<String>()
        list!!.forEach{
            listNew.add(it.name)
        }
        test_district.adapter =
                ArrayAdapter<String>(activity!!,
                        android.R.layout.simple_spinner_item,
                        listNew)
        currentCities = list
    }

    private fun updateSpinnerRegions(list: ArrayList<String>){
        val listNew = arrayListOf<String>()
        list.forEach{
            listNew.add(it)
        }
        test_region.adapter =
                ArrayAdapter<String>(activity!!,
                        android.R.layout.simple_spinner_item,
                        listNew)
    }

    companion object {
        fun hideKeyboardFrom(context: Context?, view: View) {
            val imm = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

        }
    }
}