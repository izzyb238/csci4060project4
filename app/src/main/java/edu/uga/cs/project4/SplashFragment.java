package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import edu.uga.cs.project4.MainActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {
    public static final String DEBUG_TAG = "Splash Page Saving to DB: ";

    public ArrayList<Country> countries;
    public ArrayList<Result> results;
    private Button startQuizButton;
    private Button resultsButton;

    public SplashFragment() {
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
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        startQuizButton = view.findViewById(R.id.startButton);
        resultsButton = view.findViewById(R.id.resultsButton);

        startQuizButton.setEnabled(false);
        resultsButton.setEnabled(false);

        //new InitializeDatabaseTask(requireContext(), this).execute();
        try{
            InputStream in_s = getAssets().open( "countries_data.csv" );
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            while( ( nextRow = reader.readNext() ) != null ) {
                for( int i = 0; i < nextRow.length; i++ ) {
                    String test = nextRow[i];
                    //parse string to get name and capital
                    String[] p = test.split(",");
                    //create new country object
                    String name = p[0];
                    String capital = p[1];
                    Country temp = new Country(name, capital);
                    //add to database
                    storeCountry(temp);
                }
            }
        }catch (Exception e) {
            Log.e(DEBUG_TAG, e.toString() );
        }


        startQuizButton.setOnClickListener( v ->
                ((edu.uga.cs.project4.MainActivity) requireActivity()).showFragment(new QuizFragment(), true)
        );
        resultsButton.setOnClickListener(v ->
                ((edu.uga.cs.project4.MainActivity) requireActivity()).showFragment(new ResultsFragment(), true)
        );
    }

    public void onInitializationFinished(boolean success) {
        startQuizButton.setEnabled(success);
        resultsButton.setEnabled(success);
    }
}
