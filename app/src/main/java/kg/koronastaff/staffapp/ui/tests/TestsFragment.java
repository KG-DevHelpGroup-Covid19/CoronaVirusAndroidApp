package kg.koronastaff.staffapp.ui.tests;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;

public class TestsFragment extends Fragment {
    private int questionCount = 0;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<Boolean> answers = new ArrayList<>();
    private TestsViewModel mViewModel;
    Button yesButton;
    Button noButton;
    ConstraintLayout testForm;

    TextView answerCount;
    TextView question;
    TextView secondaryText;

    private boolean sendEnabled;

    public static TestsFragment newInstance() {
        return new TestsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tests, container, false);
        questions.add("question 1");
        questions.add("question 2");
        questions.add("question 3");
        questions.add("question 4");
        questions.add("question 5");

        yesButton = root.findViewById(R.id.test_button_yes);
        noButton = root.findViewById(R.id.test_button_no);
        testForm = root.findViewById(R.id.test_form);
        answerCount = root.findViewById(R.id.textView3);
        question = root.findViewById(R.id.question);
        secondaryText = root.findViewById(R.id.test_additional_text);
        nextQuestion();

        yesButton.setOnClickListener(v->{
            if (sendEnabled){
                send(v);
            }else{
                answers.add(true);
                nextQuestion();
            }
        });

        noButton.setOnClickListener(v->{
            answers.add(false);
            nextQuestion();
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TestsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void nextQuestion(){
        if (questions.size() == questionCount){
            counterUpdate();
            loadTestingAnswer();
            questionCount++;
        } else {
            counterUpdate();
            question.setText(questions.get(questionCount));
            questionCount++;
        }
    }

    @SuppressLint("SetTextI18n")
    private void counterUpdate(){
        answerCount.setText((questionCount + 1) + getString(R.string.of) + (questions.size() + 1));
    }

    private void loadTestingAnswer(){
        question.setText(getString(R.string.test_positive));
        secondaryText.setText(getString(R.string.test_positive_second));
        testForm.setVisibility(View.VISIBLE);
        noButton.setVisibility(View.GONE);
        yesButton.setText(getString(R.string.test_profile_send));
        sendEnabled = true;
    }

    private void send(View v){
        Navigation.findNavController(v).navigate(R.id.menu_quations_covid19);
        hideKeyboardFrom(getActivity(), v);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
