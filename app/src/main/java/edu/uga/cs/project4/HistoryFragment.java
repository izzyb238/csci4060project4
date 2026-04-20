package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    private QuizResultAdapter adapter;

    public HistoryFragment() {
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
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.resultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new QuizResultAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        //task to load the quiz results
        //new LoadQuizResultsTask(requireContext(), this).excute();
    }

    public void onResultsLoaded(List<Result> results) {
        adapter.setResults(results);
    }
}