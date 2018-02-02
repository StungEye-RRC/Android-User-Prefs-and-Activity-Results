package com.stungeye.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
