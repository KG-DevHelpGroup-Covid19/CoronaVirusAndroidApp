package kg.koronastaff.staffapp.ui.tests

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.database.Base
import kotlinx.android.synthetic.main.fragment_tests.*
import kotlinx.android.synthetic.main.fragment_tests.view.*
import java.util.*

class TestsFragment : Fragment() {
    private var questionCount = 0
    private val questions = ArrayList<String>()
    private val answers = ArrayList<Boolean>()
    private var mViewModel: TestsViewModel? = null
    private var sendEnabled = false
    private lateinit var base: Base
    private lateinit var token: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_tests, container, false)
        base = Base(activity!!)


        questions.add("question 1")
        questions.add("question 2")
        questions.add("question 3")
        questions.add("question 4")
        questions.add("question 5")


        nextQuestion(root)
        root.test_button_yes.setOnClickListener{ v: View ->
            if (sendEnabled) {
                send(v)
            } else {
                answers.add(true)
                nextQuestion(root)
            }
        }
        root.test_button_no.setOnClickListener{ v: View? ->
            answers.add(false)
            nextQuestion(root)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)
        updateToken()
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
        v.textView3.text = (questionCount + 1).toString() + getString(R.string.of) + (questions.size + 1)
    }

    private fun loadTestingAnswer(v: View) {
        v.question.text = getString(R.string.test_positive)
        v.test_additional_text.text = getString(R.string.test_positive_second)
        v.test_form!!.visibility = View.VISIBLE
        v.test_button_no!!.visibility = View.GONE
        v.test_button_yes!!.text = getString(R.string.test_profile_send)
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

    companion object {
        fun hideKeyboardFrom(context: Context?, view: View) {
            val imm = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

        }
    }
}