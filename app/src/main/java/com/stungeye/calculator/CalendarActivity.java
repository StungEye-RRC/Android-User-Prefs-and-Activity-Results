package com.stungeye.calculator;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CalendarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    final String preferenceIdentifier = "com.stungeye.calculator";
    final String PREFS_AGE = "pref_age";
    final String PREFS_NAME = "pref_name";
    final int UNSPECIFIED_AGE = -1;
    public SharedPreferences prefs;

    public EditText ageField;
    public EditText nameField;
    public EditText dateField;

    public Calendar birthDate;

    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        prefs = this.getSharedPreferences(preferenceIdentifier, Context.MODE_PRIVATE);

        ageField = findViewById(R.id.age);
        nameField = findViewById(R.id.name);
        dateField = findViewById(R.id.date);

        birthDate = Calendar.getInstance();

        findViewById(R.id.date).setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View view, boolean b) {
                if (b) {
                    DatePickerDialog picker = new DatePickerDialog(CalendarActivity.this, CalendarActivity.this, birthDate.get(Calendar.YEAR), birthDate.get(Calendar.MONTH), birthDate.get(Calendar.DAY_OF_MONTH));
                    picker.show();
                }
             }
         });
    }

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-YYYY");
        Calendar today = Calendar.getInstance();
        birthDate.set(year, monthOfYear, dayOfMonth);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        dateField.setText(dateFormatter.format(birthDate.getTime()));
        ageField.setText(""+age);
        ageField.requestFocus();
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
