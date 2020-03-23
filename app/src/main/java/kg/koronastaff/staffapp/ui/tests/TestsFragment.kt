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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kg.koronastaff.staffapp.R
import kotlinx.android.synthetic.main.fragment_tests.*
import java.util.*

class TestsFragment : Fragment() {
    private var questionCount = 0
    private val questions = ArrayList<String>()
    private val answers = ArrayList<Boolean>()
    private var mViewModel: TestsViewModel? = null
    var yesButton: Button? = null
    var noButton: Button? = null
    var testForm: ConstraintLayout? = null
    var answerCount: TextView? = null
    var question: TextView? = null
    var secondaryText: TextView? = null
    private var sendEnabled = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_tests, container, false)
        questions.add("question 1")
        questions.add("question 2")
        questions.add("question 3")
        questions.add("question 4")
        questions.add("question 5")

        testForm = root.findViewById(R.id.test_form)
        answerCount = root.findViewById(R.id.textView3)
        question = root.findViewById(R.id.question)
        secondaryText = root.findViewById(R.id.test_additional_text)

        nextQuestion()
        test_button_yes.setOnClickListener(View.OnClickListener { v: View ->
            if (sendEnabled) {
                send(v)
            } else {
                answers.add(true)
                nextQuestion()
            }
        })
        test_button_no.setOnClickListener(View.OnClickListener { v: View? ->
            answers.add(false)
            nextQuestion()
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun nextQuestion() {
        if (questions.size == questionCount) {
            counterUpdate()
            loadTestingAnswer()
            questionCount++
        } else {
            counterUpdate()
            question!!.text = questions[questionCount]
            questionCount++
        }
    }

    @SuppressLint("SetTextI18n")
    private fun counterUpdate() {
        answerCount!!.text = (questionCount + 1).toString() + getString(R.string.of) + (questions.size + 1)
    }

    private fun loadTestingAnswer() {
        question!!.text = getString(R.string.test_positive)
        secondaryText!!.text = getString(R.string.test_positive_second)
        testForm!!.visibility = View.VISIBLE
        noButton!!.visibility = View.GONE
        yesButton!!.text = getString(R.string.test_profile_send)
        sendEnabled = true
    }

    private fun send(v: View) {
        Navigation.findNavController(v).navigate(R.id.menu_quations_covid19)
        hideKeyboardFrom(activity, v)
    }

    companion object {
        fun hideKeyboardFrom(context: Context?, view: View) {
            val imm = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}