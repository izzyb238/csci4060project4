package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {
    public static final String ARG_SCORE = "arg_score";
    public static final String ARG_MAX_SCORE = "arg_max_score";


    public ResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param score Parameter 1.
     * @param maxScore Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance(int score, int maxScore) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_MAX_SCORE, maxScore);
        args.putInt(ARG_MAX_SCORE, maxScore);
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
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        int score = getArguments().getInt(ARG_SCORE);
        int maxScore = getArguments().getInt(ARG_MAX_SCORE);

        TextView scoreText = view.findViewById(R.id.resultScoreText);
        Button newQuizButton = view.findViewById(R.id.newQuizButton);
        Button historyButton = view.findViewById(R.id.historyButton);

        scoreText.setText("Your Score: " + score + " / " + maxScore);

        newQuizButton.setOnClickListener(v ->
                ((MainActivity) requireActivity()).showFragment(new QuizFragment(), false)
        );

        historyButton.setOnClickListener(v ->
                ((MainActivity) requireActivity()).showFragment(new HistoryFragment(), true)
        );
    }
}
