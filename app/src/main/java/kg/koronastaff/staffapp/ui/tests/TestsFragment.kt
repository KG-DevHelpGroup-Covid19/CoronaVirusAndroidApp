package kg.koronastaff.staffapp.ui.tests

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.database.Base
import kg.koronastaff.staffapp.database.Cache
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.models.TestQuestion
import kg.koronastaff.staffapp.models.TestResults
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_tests.*
import kotlinx.android.synthetic.main.fragment_tests.view.*
import kotlin.collections.ArrayList

class TestsFragment : Fragment() {
    private var questionCount = 0
    private val questions = ArrayList<String>()
    private var mViewModel: TestsViewModel? = null
    private var sendEnabled = false
    private lateinit var base: Base
    private lateinit var token: String
    private lateinit var cities: ArrayList<City>
    private lateinit var cache: Cache

    private lateinit var testRegion: Spinner
    private lateinit var currentCities: ArrayList<City>

    private var testResults: TestResults = TestResults()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_tests, container, false)
        base = Base(activity!!)
        testRegion = root.test_region
        cache = Cache(activity!!)

        mViewModel?.getQuestions()?.subscribe{

        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)
        cities = cache.getCities()

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

    private fun createButtons(list: ArrayList<TestQuestion.Choice>, v: View){
        var i = 0
        list.forEach{choice ->
            var button = v.test_button_1
            if (i % 2 == 1){
                button = v.test_button_2
            }
            button.text = choice.sc_value
            button.visibility = View.VISIBLE
            button.setOnClickListener {
                choose(choice, it)
            }
            v.buttons_container.addView(button)
            i++
        }
    }

    private fun choose(choice: TestQuestion.Choice, v: View){

    }

    private fun nextQuestion(v: View) {
        if (questions.size == questionCount) {
            counterUpdate(v)
            loadTestingAnswer(v)
            questionCount++
        } else {
            counterUpdate(v)
            v.question.text = questions[questionCount]
            questionCount++
        }
    }

    @SuppressLint("SetTextI18n")
    private fun counterUpdate(v: View) {
        v.textView3.text =
                (questionCount + 1).toString() + " "+
                        getString(R.string.of) + " " + (questions.size + 1)
    }

    private fun loadTestingAnswer(v: View) {
        v.question.text = getString(R.string.test_positive)
        v.test_additional_text.text = getString(R.string.test_positive_second)
        v.test_form!!.visibility = View.VISIBLE
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

    fun updateSpinnerRegions(list: ArrayList<String>){
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