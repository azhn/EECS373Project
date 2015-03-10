package com.azhen.bikingapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class HistoryActivity extends ActionBarActivity {

    TextView bikeTrackId;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        /*ArrayList<HashMap<String, String>> bikeHistoryList = dbTools.getAllBikeHistory();

        if (bikeHistoryList.size() != 0) {
            Log.d("MY_TAG", "onCreate() for HistoryActivity with arraylist size > 0");
            //ListView bikeHistoryListView = getListView();
            ListView bikeHistoryListView = (ListView) findViewById(R.id.listFor);
            bikeHistoryListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    bikeTrackId = (TextView) view.findViewById(R.id.bikeTrackId);

                    String bikeTrackIdValue = bikeTrackId.getText().toString();

                    Intent intent = new Intent(getApplication(), SingleHistoryActivity.class);

                    intent.putExtra("bikeTrackId", bikeTrackIdValue);

                    startActivity(intent);

                }
            });

            ListAdapter adapter = new SimpleAdapter(
                    HistoryActivity.this, bikeHistoryList, R.layout.bike_tracker_entry,
                    new String[] {"bikeTrackId", "dateTime", "averageSpeed", "rideFeeling"},
                    new int[] {R.id.bikeTrackId, R.id.dateTimeTextView, R.id.averageSpeedTextView, R.id.rideFeelingTextView});
            bikeHistoryListView.setAdapter(adapter);
            //setListAdapter(adapter);
        } else {
            Log.d("MY_TAG", "onCreate() for HistoryActivity with arraylist size == 0");
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<HashMap<String, String>> bikeHistoryList = dbTools.getAllBikeHistory();
        ListView bikeHistoryListView = (ListView) findViewById(R.id.listFor);

        if (bikeHistoryList.size() != 0) {
            Log.d("MY_TAG", "onCreate() for HistoryActivity with arraylist size > 0");
            //ListView bikeHistoryListView = getListView();
            //ListView bikeHistoryListView = (ListView) findViewById(R.id.listFor);
            bikeHistoryListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    bikeTrackId = (TextView) view.findViewById(R.id.bikeTrackId);

                    String bikeTrackIdValue = bikeTrackId.getText().toString();

                    Intent intent = new Intent(getApplication(), SingleHistoryActivity.class);

                    intent.putExtra("bikeTrackId", bikeTrackIdValue);

                    startActivity(intent);

                }
            });

            ListAdapter adapter = new SimpleAdapter(
                    HistoryActivity.this, bikeHistoryList, R.layout.bike_tracker_entry,
                    new String[] {"bikeTrackId", "dateDate", "averageSpeed", "rideFeeling"},
                    new int[] {R.id.bikeTrackId, R.id.dateDateTextView, R.id.averageSpeedTextView, R.id.rideFeelingTextView});
            bikeHistoryListView.setAdapter(adapter);
            //setListAdapter(adapter);
        } else {
            bikeHistoryListView.setAdapter(null);
            Log.d("MY_TAG", "onCreate() for HistoryActivity with arraylist size == 0");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_history, menu);

        return (super.onCreateOptionsMenu(menu));
    }



}
