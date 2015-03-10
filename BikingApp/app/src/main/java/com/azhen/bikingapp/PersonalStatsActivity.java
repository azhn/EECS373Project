package com.azhen.bikingapp;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PersonalStatsActivity extends ActionBarActivity {

    SharedPreferences mPersonalStatsActivity;

    // Local variables for this Activity
    private double HEIGHT;
    private double WEIGHT;
    private int AGE;
    private boolean GENDER;

    // References to views on Layout
    EditText HeightEditText;
    EditText WeightEditText;
    EditText AgeEditText;
    RadioGroup GenderRadioGroup;
    RadioButton MaleRadioButton;
    RadioButton FemaleRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_stats);

        HeightEditText = (EditText) findViewById(R.id.HeightEditText);
        WeightEditText = (EditText) findViewById(R.id.WeightEditText);
        AgeEditText = (EditText) findViewById(R.id.AgeEditText);
        GenderRadioGroup = (RadioGroup) findViewById(R.id.GenderRadioGroup);
        MaleRadioButton = (RadioButton) findViewById(R.id.MaleRadioButton);
        FemaleRadioButton = (RadioButton) findViewById(R.id.FemaleRadioButton);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPersonalStats();
    }

    @Override
    protected void onPause() {
        super.onPause();

        savePersonalStats();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_stats, menu);
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


    // ******************* CUSTOM METHODS ******************** //

    // Custom putDouble and getDouble since SharedPreferences doesn't support
    SharedPreferences.Editor putDouble(final SharedPreferences.Editor edit, final String key, final double value) {
        return edit.putLong(key, Double.doubleToRawLongBits(value));
    }
    double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }


    public void loadPersonalStats() {
        // Access the device's key-value storage
        mPersonalStatsActivity = getSharedPreferences(HomeScreenActivity.HOMESCREEN_KEY, MODE_PRIVATE);

        double defaultHEIGHT = 0;
        int defaultWEIGHT = 0;
        int defaultAGE = 0;
        boolean defaultGENDER = true;

        // Read the user's HEIGHT, or a 0 if not found
        HEIGHT = getDouble(mPersonalStatsActivity, HomeScreenActivity.HEIGHT_KEY, defaultHEIGHT);
        // Read the user's WEIGHT, or a 0 if not found
        WEIGHT = getDouble(mPersonalStatsActivity, HomeScreenActivity.WEIGHT_KEY, defaultWEIGHT);
        // Read the user's HEIGHT, or a 0 if not found
        AGE = mPersonalStatsActivity.getInt(HomeScreenActivity.AGE_KEY, defaultAGE);
        // Read the user's HEIGHT, or a 0 if not found
        GENDER = mPersonalStatsActivity.getBoolean(HomeScreenActivity.GENDER_KEY, defaultGENDER);


        // Set the EditTexts to contain the loaded values
        HeightEditText.setText(String.valueOf(HEIGHT));
        WeightEditText.setText(String.valueOf(WEIGHT));
        AgeEditText.setText(String.valueOf(AGE));
        if (GENDER) {
            MaleRadioButton.setChecked(true);
            FemaleRadioButton.setChecked(false);
        } else {
            MaleRadioButton.setChecked(false);
            FemaleRadioButton.setChecked(true);
        }

    }

    public void savePersonalStats() {

        updateLocalPersonalStats();

        SharedPreferences.Editor editor = mPersonalStatsActivity.edit();
        editor = putDouble(editor, HomeScreenActivity.HEIGHT_KEY, HEIGHT);
        editor = putDouble(editor, HomeScreenActivity.WEIGHT_KEY, WEIGHT);
        editor.putInt(HomeScreenActivity.AGE_KEY, AGE);
        editor.putBoolean(HomeScreenActivity.GENDER_KEY, GENDER);

        editor.commit();
    }

    private void updateLocalPersonalStats() {
        HEIGHT = Double.parseDouble(HeightEditText.getText().toString());
        WEIGHT = Double.parseDouble(WeightEditText.getText().toString());
        AGE = Integer.parseInt(AgeEditText.getText().toString());
        GENDER = (MaleRadioButton.isChecked());
    }


}
