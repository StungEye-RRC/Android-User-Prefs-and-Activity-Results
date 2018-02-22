package com.stungeye.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    final String preferenceIdentifier = "com.stungeye.calculator";
    final String PREFS_AGE = "pref_age";
    final String PREFS_NAME = "pref_name";
    final int UNSPECIFIED_AGE = -1;
    public SharedPreferences prefs;

    public EditText ageField;
    public EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        prefs = this.getSharedPreferences(preferenceIdentifier, Context.MODE_PRIVATE);

        ageField = findViewById(R.id.age);
        nameField = findViewById(R.id.name);
    }

    /* Make the "up/home" button act like the back button
       so that we always return our result Intent.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        String name = nameField.getText().toString().trim();
        String ageString  = ageField.getText().toString().trim();

        data.putExtra(MainActivity.EXTRA_NAME, name);
        data.putExtra(MainActivity.EXTRA_AGE, ageString);
        setResult(RESULT_OK, data);
        super.onBackPressed();
    }

    public void loadPrefs(View view) {
        String name = prefs.getString(PREFS_NAME, null);
        int age = prefs.getInt(PREFS_AGE, UNSPECIFIED_AGE);

        String ageString = "";

        if (age != UNSPECIFIED_AGE) {
            ageString += age;
        }

        nameField.setText(name);
        ageField.setText(ageString);

        Toast.makeText(getApplicationContext(), "Name and Age Loaded", Toast.LENGTH_LONG).show();
    }

    public void savePrefs(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        String name = nameField.getText().toString().trim();
        String ageString  = ageField.getText().toString().trim();
        int age;

        if (ageString.equals("")) {
            age = UNSPECIFIED_AGE;
        } else {
            age = Integer.valueOf(ageString);
        }

        editor.putInt(PREFS_AGE, age);
        editor.putString(PREFS_NAME, name);

        editor.commit();

        Toast.makeText(getApplicationContext(), "Name and Age Saved", Toast.LENGTH_LONG).show();
    }
}
