package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private static final String ARG_QUESTION_INDEX = "arg_question_index";
    private static final String STATE_QUIZ = "state_quiz";


    private int questionIndex;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param questionIndex integer value for the index of the question.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(int questionIndex) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_QUESTION_INDEX, questionIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        questionIndex = getArguments().getInt(ARG_QUESTION_INDEX);

        QuizFragment host = (QuizFragment) getParentFragment();
        Quiz quiz = host.getQuiz();
        Question question = quiz.getQuestions().get(questionIndex);

        TextView questionText = view.findViewById(R.id.questionText);
        RadioGroup radioGroup = view.findViewById(R.id.capitalRadioGroup);
        RadioButton option1 = view.findViewById(R.id.option1);
        RadioButton option2 = view.findViewById(R.id.option2);
        RadioButton option3 = view.findViewById(R.id.option3);
        Button nextButton = view.findViewById(R.id.nextButton);

        questionText.setText("Name the capital city of " + question.getCountry().getCountryName());

        option1.setText(question.getOptions().get(0));
        option2.setText(question.getOptions().get(1));
        option3.setText(question.getOptions().get(2));

        if (question.getSelectedCapital() == 0) option1.setChecked(true);
        else if (question.getSelectedCapital() == 1) option2.setChecked(true);
        else if (question.getSelectedCapital() == 2) option3.setChecked(true);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.option1) {
                question.setSelectedCapital(0);
            } else if (checkedId == R.id.option2) {
                question.setSelectedCapital(1);
            } else if (checkedId == R.id.option3) {
                question.setSelectedCapital(2);
            }
            quiz.updateScore();
        });

        if (questionIndex == quiz.getQuestions().size() - 1) {
            nextButton.setText("Finish Quiz");
        }

        nextButton.setOnClickListener(v -> {
            if (!question.isAnswered()) {
                Toast.makeText(requireContext(), "Please select an answer first.", Toast.LENGTH_SHORT).show();
                return;
            }
            quiz.updateScore();
            host.goToNextPage();
        });
    }
}