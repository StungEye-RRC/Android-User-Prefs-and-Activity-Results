package com.stungeye.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final int NAME_AGE_REQUEST = 0;
    static final String EXTRA_AGE = "extra_age";
    static final String EXTRA_NAME = "extra_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NAME_AGE_REQUEST && resultCode == Activity.RESULT_OK) {

            EditText editName = findViewById(R.id.name);
            EditText editAge = findViewById(R.id.age);

            editName.setText(data.getStringExtra(EXTRA_NAME));
            editAge.setText(data.getStringExtra(EXTRA_AGE));
        }
    }

    public void startCalendar(View view) {
        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        startActivityForResult(intent, NAME_AGE_REQUEST);
    }

    public void calculate(View view) {
        EditText editOperand1 = findViewById(R.id.num1);
        EditText editOperand2 = findViewById(R.id.num2);
        int operand1 = Integer.valueOf(editOperand1.getText().toString());
        int operand2 = Integer.valueOf(editOperand2.getText().toString());
        float result = 0;

        switch (view.getId()) {
            case R.id.add:
                result = operand1 + operand2;
                break;
            case R.id.subtract:
                result = operand1 - operand2;
                break;
            case R.id.multiply:
                result = operand1 * operand2;
                break;
            case R.id.divide:
                if (operand2 != 0) {
                    result = (float)operand1 / (float)operand2;
                }
                break;
            case R.id.factorial:
                result = 1;
                for (int i = 1; i <= operand1; i++) {
                    result *= i;
                }
                break;
        }


        EditText editText = findViewById(R.id.result);
        editText.setText(""+result);
    }
}
