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
 * This class stores and restores countries in the database.
 */
public class CountryData {
    public static final String DEBUG_TAG = "CountryData";
    private SQLiteDatabase db;
    private SQLiteOpenHelper countryDbHelper;
    private static final String[] allColumns = {
            CountryDBHelper.COUNTRIES_COLUMN_ID,
            CountryDBHelper.COUNTRIES_COLUMN_NAME,
            CountryDBHelper.COUNTRIES_COLUMN_CAPITAL
    };

    public CountryData (Context context) {
        countryDbHelper = CountryDBHelper.getInstance(context);
    }

    public void open() {
        db = countryDbHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "country db open");
    }

    public void close() {
        if(countryDbHelper != null) {
            countryDbHelper.close();
            Log.d(DEBUG_TAG, "db closed");
        }
    }

    public boolean isDBOpen() {return db.isOpen();}

    /** This method retrieves all countries and returns them
     * as a list. */
    public List<Country> retrieveAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try{
            cursor = db.query(CountryDBHelper.TABLE_COUNTRIES, allColumns, null, null, null, null, null);
            if(cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    //get each row
                    columnIndex = cursor.getColumnIndex(CountryDBHelper.COUNTRIES_COLUMN_ID);
                    long id = cursor.getLong(columnIndex);

                    columnIndex = cursor.getColumnIndex(CountryDBHelper.COUNTRIES_COLUMN_NAME);
                    String name = cursor.getString(columnIndex);

                    columnIndex = cursor.getColumnIndex(CountryDBHelper.COUNTRIES_COLUMN_CAPITAL);
                    String capital = cursor.getString(columnIndex);

                    //create new Country object and set its state to retrieved values
                    Country country = new Country(name, capital);
                    country.setId(id);
                    countries.add(country);
                    Log.d(DEBUG_TAG, "Retrieved Country: " + country);
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
        return countries;
    } //retrieve all countries

    public Country storeCountry (Country country) {
        ContentValues vals = new ContentValues();
        vals.put(CountryDBHelper.COUNTRIES_COLUMN_NAME, country.getCountryName());
        vals.put(CountryDBHelper.COUNTRIES_COLUMN_CAPITAL, country.getCapitalCity());

        long id = db.insert(CountryDBHelper.TABLE_COUNTRIES, null, vals);
        country.setId(id);
        Log.d(DEBUG_TAG, "Stored new country with id: " + String.valueOf(country.getId()) );
        return country;
    }
}