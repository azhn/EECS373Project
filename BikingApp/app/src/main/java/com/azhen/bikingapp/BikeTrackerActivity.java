package com.azhen.bikingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


public class BikeTrackerActivity extends ActionBarActivity {

    EditText averageSpeed;
    EditText averageIncline;
    EditText dateDate;
    EditText dateTime;
    Button updateStuff;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_tracker);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


        averageSpeed = (EditText) findViewById(R.id.averageSpeedEditText);
        averageIncline = (EditText) findViewById(R.id.averageInclineEditText);
        dateDate = (EditText) findViewById(R.id.dateDateEditText);
        dateTime = (EditText) findViewById(R.id.dateTimeEditText);

        dateDate.setText(dateFormat.format(new Date()));

        updateStuff = (Button) findViewById(R.id.updateButton);
        updateStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> tempMap = new HashMap<String, String>();

                //tempMap.put("dateTime", getDateCurrentTimeZone(System.currentTimeMillis()*));
                tempMap.put("dateDate", dateFormat.format(new Date()));
                tempMap.put("dateTime", timeFormat.format(new Date()));
                tempMap.put("averageSpeed", averageSpeed.getText().toString());
                tempMap.put("averageIncline", averageIncline.getText().toString());
                tempMap.put("rideFeeling", "2");

                dbTools.insertBikeHistory(tempMap);


            }
        });


    }

    public void updateRideFeeling(String feels) {
        switch(feels) {
            case "0":
                break;
        }
    }

    public  String getDateCurrentTimeZone(long timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }

    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bike_tracker, menu);
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


}
