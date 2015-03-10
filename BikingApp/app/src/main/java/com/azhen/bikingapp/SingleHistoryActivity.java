package com.azhen.bikingapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


public class SingleHistoryActivity extends ActionBarActivity {

    TextView dateDateTextView;
    TextView dateTimeTextView;
    TextView averageSpeedTextView;
    TextView averageInclineTextView;
    ImageView rideFeelingImageView;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_history);

        dateDateTextView = (TextView) findViewById(R.id.dateDateTextView);
        dateTimeTextView = (TextView) findViewById(R.id.dateTimeTextView);
        averageSpeedTextView = (TextView) findViewById(R.id.averageSpeedTextView);
        averageInclineTextView = (TextView) findViewById(R.id.averageInclineTextView);
        rideFeelingImageView = (ImageView) findViewById(R.id.rideFeelingImageView);

        Intent intent = getIntent();

        String bikeTrackId = intent.getStringExtra("bikeTrackId");

        HashMap<String, String> bikeHistoryMap = dbTools.getBikeHistory(bikeTrackId);

        if (bikeHistoryMap.size() != 0) {
            dateDateTextView.setText(bikeHistoryMap.get("dateDate"));
            dateTimeTextView.setText(bikeHistoryMap.get("dateTime"));
            averageSpeedTextView.setText(bikeHistoryMap.get("averageSpeed"));
            averageInclineTextView.setText(bikeHistoryMap.get("averageIncline"));

            switch (bikeHistoryMap.get("rideFeeling")) {
                case "0": rideFeelingImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoticon_0));
                break;
                case "1": rideFeelingImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoticon_1));
                    break;
                case "2": rideFeelingImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoticon_2));
                    break;
                case "3": rideFeelingImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoticon_3));
                    break;
                case "4": rideFeelingImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoticon_4));
                    break;
                default: rideFeelingImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoticon_0));
                    break;
            }

        }



    }

    /*@Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/

    public void deleteBikeHistory(View view) {
        Intent Intent = getIntent();
        String bikeTrackId = Intent.getStringExtra("bikeTrackId");

        dbTools.deleteBikeHistory(bikeTrackId);

        //this.callHistoryActivity(view);
        finish();
    }

    /*public void callHistoryActivity(View view) {
        Intent Intent = new Intent(getApplication(), HistoryActivity.class);

        startActivity(Intent);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_history, menu);
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
