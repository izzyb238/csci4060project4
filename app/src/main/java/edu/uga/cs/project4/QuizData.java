package edu.uga.cs.project4;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores and restores results in the database.
 */
public class QuizData {
    public static final String DEBUG_TAG = "QuizData";
    private SQLiteDatabase db;
    private SQLiteOpenHelper quizDBHelper;
    private static final String[] allCols = {
            QuizDBHelper.QUIZZES_COLUMN_ID,
            QuizDBHelper.QUIZZES_COLUMN_SCORE,
            QuizDBHelper.QUIZZES_COLUMN_DATE
    };

    public QuizData (Context context) {quizDBHelper = QuizDBHelper.getInstance(context);}

    public void open() {
        db = quizDBHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "quiz db open");
    }

    public void close() {
        if(quizDBHelper != null) {
            quizDBHelper.close();
            Log.d(DEBUG_TAG, "quiz db closed");
        }
    }

    /** This method retrieves all quizzes and returns them
     * as a list. */
    public List<Result> retrieveAllQuizzes() {
        ArrayList<Result> results = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            cursor = db.query(QuizDBHelper.TABLE_RESULTS, allCols, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    //get each row
                    columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZZES_COLUMN_ID);
                    long id = cursor.getLong(columnIndex);

                    columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZZES_COLUMN_SCORE);
                    int score = cursor.getInt(columnIndex);

                    columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZZES_COLUMN_DATE);
                    String date = cursor.getString(columnIndex);

                    Result result = new Result(id, date, score);
                    results.add(result);
                    Log.d(DEBUG_TAG, "Retrieved Result: " + result);
                }//while
            }//if
            if(cursor != null) {
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
            } else {
                Log.d(DEBUG_TAG, "Number of records from DB: 0");
            }
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
        return results;
    }

    public Result storeResult (Result result) {
        ContentValues vals = new ContentValues();
        vals.put(QuizDBHelper.QUIZZES_COLUMN_SCORE, result.getScore());
        vals.put(QuizDBHelper.QUIZZES_COLUMN_DATE, result.getDate());

        long id = db.insert(QuizDBHelper.TABLE_RESULTS, null, vals);
        result.setId(id);
        Log.d(DEBUG_TAG, "Stored new quiz with id: " + String.valueOf(result.getId()) );
        return result;
    }

}
