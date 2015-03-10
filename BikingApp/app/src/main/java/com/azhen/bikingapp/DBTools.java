package com.azhen.bikingapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBTools extends SQLiteOpenHelper {
    public DBTools(Context applicationContext) {
        super(applicationContext, "biketrackhistory.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE bikehistory ( bikeTrackId INTEGER PRIMARY KEY, dateDate TEXT, " +
                " dateTime TEXT, averageSpeed TEXT, averageIncline TEXT, rideFeeling TEXT)";

        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS bikehistory";

        database.execSQL(query);
        onCreate(database);
    }

    public void insertBikeHistory(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("dateDate", queryValues.get("dateDate"));
        values.put("dateTime", queryValues.get("dateTime"));
        values.put("averageSpeed", queryValues.get("averageSpeed"));
        values.put("averageIncline", queryValues.get("averageIncline"));
        values.put("rideFeeling", queryValues.get("rideFeeling"));

        database.insert("bikehistory", null, values);

        database.close();
        Log.d("MY_TAG", "insertBikeHistory()");
    }

    public int updateBikeHistory(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("dateDate", queryValues.get("dateDate"));
        values.put("dateTime", queryValues.get("dateTime"));
        values.put("averageSpeed", queryValues.get("averageSpeed"));
        values.put("averageIncline", queryValues.get("averageIncline"));
        values.put("rideFeeling", queryValues.get("dateTime"));

       return database.update("bikehistory", values, "bikeTrackId" + " =?", new String[]{queryValues.get("bikeTrackId")});
    }

    public void deleteBikeHistory(String bikeTrackId) {
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM bikehistory WHERE bikeTrackId='" + bikeTrackId + "'";

        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllBikeHistory() {
        ArrayList<HashMap<String, String>> bikeHistoryArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM bikehistory ORDER BY date(dateDate) DESC, time(dateTime) DESC";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> bikehistoryMap = new HashMap<String, String>();

                bikehistoryMap.put("bikeTrackId", cursor.getString(0));
                bikehistoryMap.put("dateDate", cursor.getString(1));
                bikehistoryMap.put("dateTime", cursor.getString(2));
                bikehistoryMap.put("averageSpeed", cursor.getString(3));
                bikehistoryMap.put("averageIncline", cursor.getString(4));
                bikehistoryMap.put("rideFeeling", cursor.getString(5));

                bikeHistoryArrayList.add(bikehistoryMap);
            } while (cursor.moveToNext());
        }

        return bikeHistoryArrayList;

    }


    public HashMap<String, String> getBikeHistory(String bikeTrackId) {
        HashMap<String, String> bikehistoryMap = new HashMap<String, String>();

        SQLiteDatabase database = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM bikehistory WHERE bikeTrackId ='" + bikeTrackId + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                bikehistoryMap.put("bikeTrackId", cursor.getString(0));
                bikehistoryMap.put("dateDate", cursor.getString(1));
                bikehistoryMap.put("dateTime", cursor.getString(2));
                bikehistoryMap.put("averageSpeed", cursor.getString(3));
                bikehistoryMap.put("averageIncline", cursor.getString(4));
                bikehistoryMap.put("rideFeeling", cursor.getString(5));
            } while (cursor.moveToNext());
        }

        return bikehistoryMap;

    }




}
