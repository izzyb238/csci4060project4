package edu.uga.cs.project4;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is a SQLiteOpenHelper class, it creates,
 * updates, and deletes databases for an app.
 */
public class CountryDBHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "Country DBHelper";
    private static final String DB_NAME = "countries.db";
    private static final int DB_VERSION = 1;

    /** all table and column names */
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRIES_COLUMN_ID = "_id";
    public static final String COUNTRIES_COLUMN_NAME = "name";
    public static final String COUNTRIES_COLUMN_CAPITAL = "capital";
    private static CountryDBHelper helperInstance;

    /** This method creates the database table*/
    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
            + COUNTRIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COUNTRIES_COLUMN_NAME + " TEXT, "
            + COUNTRIES_COLUMN_CAPITAL + " TEXT" + ")" ;

    private CountryDBHelper (Context context) {super (context,DB_NAME, null, DB_VERSION);}

    /** This method is the access method for the single instance of this class. */
    public synchronized static CountryDBHelper getInstance(Context context) {
        if (helperInstance == null) {
            helperInstance = new CountryDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    /** Creates the databate if it doesn't exist yet*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COUNTRIES);
        Log.d(DEBUG_TAG, "Table " + TABLE_COUNTRIES + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_COUNTRIES + " upgraded");
    }
}
