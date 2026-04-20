package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";

    private static final String ARG_QUESTION_INDEX = "arg_question_index";
    private static final String STATE_QUIZ = "state_quiz";

    private ViewPager2 viewPager;
    private Quiz quiz;
    private boolean resultSaved = false;

    public List<Country> countries;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.questionViewPager);

        if (savedInstanceState != null) {
            quiz = (Quiz) savedInstanceState.getSerializable(STATE_QUIZ);
            if (quiz != null) {
                setupPager();
                viewPager.setCurrentItem(quiz.getCurrentIndex(), false);
                return;
            }
        }
        //new LoadCountriesTask (task that connects to the database)
        countries = retrieveAllCountries();
    }

    public void onCountriesLoaded(List<Country> countries) {
        quiz = QuizGenerator.generateQuiz(countries);
        setupPager();
    }

    public void setupPager() {
        QuestionPagerAdapter adapter = new QuestionPagerAdapter(this, quiz);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(true);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                quiz.setCurrentIndex(position);
                quiz.updateScore();

                Log.d(TAG, "Current page: " + position + ", answered=" + quiz.getQuestionsAnswered() + ", score=" + quiz.getScore());

                if (position == quiz.getQuestions().size()) {
                    finishQuizIfNeeded();
                }
            }
        });
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void goToNextPage() {
        if (viewPager.getCurrentItem() < quiz.getQuestions().size()) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }

    private void finishQuizIfNeeded() {
        if (resultSaved) return;

        quiz.updateScore();
        if (!quiz.isFinished()) {
            return;
        }

        resultSaved = true;

        String date = new SimpleDateFormat("YYYY-MM-DD HH:mm", Locale.getDefault())
                .format(new Date());

        //new SaveQuizResultTask

        ((MainActivity) requireActivity()).showFragment(
                ResultsFragment.newInstance(quiz.getScore(), quiz.getMaxScore()),
                false
        );
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (quiz != null) {
            quiz.updateScore();
            outState.putSerializable(STATE_QUIZ, quiz);
        }
    }
}
