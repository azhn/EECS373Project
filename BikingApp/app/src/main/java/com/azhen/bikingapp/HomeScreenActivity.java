package com.azhen.bikingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class HomeScreenActivity extends ActionBarActivity {

    public static final String HOMESCREEN_KEY = "com.azhen.bikingapp.HOMESCREEN_KEY";
    public static final String HEIGHT_KEY = "com.azhen.bikingapp.HEIGHT_KEY";
    public static final String WEIGHT_KEY = "com.azhen.bikingapp.WEIGHT_KEY";
    public static final String AGE_KEY = "com.azhen.bikingapp.AGE_KEY";
    public static final String GENDER_KEY = "com.azhen.bikingapp.GENDER_KEY";

    DBTools dbTools = new DBTools(this);


    SharedPreferences mHomeScreenSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoBikeTracker(View view) {
        Intent intent = new Intent(this, BikeTrackerActivity.class);

        startActivity(intent);
    }

    public void gotoPersonalStats(View view) {
        Intent intent = new Intent(this, PersonalStatsActivity.class);

        startActivity(intent);
    }

    public void gotoHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);

        startActivity(intent);
    }

}
