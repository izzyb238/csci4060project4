package edu.uga.cs.project4;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.*;

/**
 * This is a SQLiteOpenHelper class, it creates,
 * updates, and deletes databases for an app.
 */
public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "Quiz DBHelper";
    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 1;

    /** all table and column names */
    public static final String TABLE_RESULTS = "quiz results";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_SCORE = "score";
    public static final String QUIZZES_COLUMN_DATE = "date";
    private static QuizDBHelper helperInstance;

    private static final String CREATE_QUIZZES =
            "create table " + TABLE_RESULTS + " ("
            + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + QUIZZES_COLUMN_SCORE + " INTEGER, "
            + QUIZZES_COLUMN_DATE + " TEXT " + ")";

    private QuizDBHelper (Context context) {super (context, DB_NAME, null, DB_VERSION);}

    /** This method is the access method for the single instance of this class. */
    public synchronized static QuizDBHelper getInstance(Context context) {
        if(helperInstance == null) {
            helperInstance = new QuizDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUIZZES);
        Log.d(DEBUG_TAG, "Table " + TABLE_RESULTS + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_RESULTS);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_RESULTS + " upgraded");
    }
}
