package com.nigdroid.calculator_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";
    private String currentOperator = "";
    private double firstOperand = 0;
    private DecimalFormat decimalFormat = new DecimalFormat("#.#######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Number Buttons
        int[] numberButtonIds = {R.id.zeroButton, R.id.oneButton, R.id.twoButton, R.id.threeButton,
                R.id.fourButton, R.id.fiveButton, R.id.sixButton, R.id.sevenButton,
                R.id.eightButton, R.id.nineButton};
        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onNumberClick);
        }

        // Operator Buttons
        int[] operatorButtonIds = {R.id.plusButton, R.id.minusButton, R.id.multiplyButton,
                R.id.divideButton};
        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onOperatorClick);
        }

        // Other Buttons
        findViewById(R.id.dotButton).setOnClickListener(this::onDotClick);
        findViewById(R.id.clearButton).setOnClickListener(this::onClearClick);
        findViewById(R.id.equalButton).setOnClickListener(this::onEqualClick);
        findViewById(R.id.plusMinusButton).setOnClickListener(this::onPlusMinusClick);
        findViewById(R.id.percentButton).setOnClickListener(this::onPercentClick);
    }

    private void onNumberClick(View view) {
        Button button = (Button) view;
        currentInput += button.getText().toString();
        updateResult();
    }

    private void onOperatorClick(View view) {
        Button button = (Button) view;
        if (!currentInput.isEmpty()) {
            currentOperator = button.getText().toString();
            firstOperand = Double.parseDouble(currentInput);
            currentInput = "";
        }
    }

    private void onDotClick(View view) {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            updateResult();
        }
    }

    private void onClearClick(View view) {
        currentInput = "";
        currentOperator = "";
        firstOperand = 0;
        updateResult();
    }

    private void onEqualClick(View view) {
        if (!currentInput.isEmpty() && !currentOperator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (currentOperator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }

            currentInput = decimalFormat.format(result);
            currentOperator = "";
            firstOperand = 0;
            updateResult();
        }
    }
    private void onPercentClick(View view){
        if (!currentInput.isEmpty()){
            double value = Double.parseDouble(currentInput);
            currentInput = decimalFormat.format(value/100);
            updateResult();
        }
    }

    private void onPlusMinusClick(View view){
        if (!currentInput.isEmpty()){
            double value = Double.parseDouble(currentInput);
            currentInput = decimalFormat.format(value * -1);
            updateResult();
        }
    }
    private void updateResult() {
        if (currentInput.isEmpty()){
            resultTextView.setText("0");
        }else{
            resultTextView.setText(currentInput);
        }
    }
}