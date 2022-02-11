package com.HaydenG.simplycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private enum Operator {
        ADD('+'), SUBTRACT('-'), MULTIPLY('×'), DIVIDE('÷');

        private final char character;

        Operator(char character) {
            this.character = character;
        }

        public char getCharacter() {
            return character;
        }
    }

    private Operator currentOperator;
    private String num1;
    private String num2;
    private boolean showingResult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupOnClicks();
        clear();
    }

    private void setupOnClicks() {
        Button[] buttons = new Button[]{
                findViewById(R.id.button_1), findViewById(R.id.button_2),
                findViewById(R.id.button_3), findViewById(R.id.button_4),
                findViewById(R.id.button_5), findViewById(R.id.button_6),
                findViewById(R.id.button_7), findViewById(R.id.button_8),
                findViewById(R.id.button_9), findViewById(R.id.button_0),
                findViewById(R.id.button_mult), findViewById(R.id.button_sub),
                findViewById(R.id.button_add), findViewById(R.id.button_equals),
                findViewById(R.id.button_divide), findViewById(R.id.button_decpoint),
                findViewById(R.id.button_clear)
        };
        for (Button button : buttons) {
            button.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(view);
                }
            });
        }
    }

    private void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_0:
                setNum('0');
                break;
            case R.id.button_1:
                setNum('1');
                break;
            case R.id.button_2:
                setNum('2');
                break;
            case R.id.button_3:
                setNum('3');
                break;
            case R.id.button_4:
                setNum('4');
                break;
            case R.id.button_5:
                setNum('5');
                break;
            case R.id.button_6:
                setNum('6');
                break;
            case R.id.button_7:
                setNum('7');
                break;
            case R.id.button_8:
                setNum('8');
                break;
            case R.id.button_9:
                setNum('9');
                break;
            case R.id.button_decpoint:
                setNum('.');
                break;
            case R.id.button_mult:
                currentOperator = Operator.MULTIPLY;
                break;
            case R.id.button_divide:
                currentOperator = Operator.DIVIDE;
                break;
            case R.id.button_add:
                currentOperator = Operator.ADD;
                break;
            case R.id.button_sub:
                currentOperator = Operator.SUBTRACT;
                break;
            case R.id.button_equals:
                calculate();
                break;
            case R.id.button_clear:
                clear();
                break;
        }
    }

    private void setNum(char i) {
        if (currentOperator == null) {
            num1 = num1 + i;
        } else {
            num2 = num2 + i;
        }
    }

    private void refreshScreen(boolean showResult) {
        String screenText;
        TextView calcScreen = (TextView) findViewById(R.id.calcScreen);
        if (showResult) {
            screenText = num1.toString();
            showingResult = true;   // Consider removing
        } else {
            screenText = num1.toString() + " " + currentOperator.getCharacter()
                    + " " + num2.toString();
            showingResult = false;  // Consider removing
        }
        calcScreen.setText(screenText);
    }

    protected void clear() {
        num1 = "0";
        num2 = "0";
        refreshScreen(true);
    }

    protected void calculate() {
        BigDecimal numCalc1 = new BigDecimal(num1);
        BigDecimal numCalc2 = new BigDecimal(num2);

        switch (currentOperator) {
            case ADD:
                numCalc1 = numCalc1.add(numCalc2);
                break;
            case SUBTRACT:
                numCalc1 = numCalc1.subtract(numCalc2);
                break;
            case MULTIPLY:
                numCalc1 = numCalc1.multiply(numCalc2);
                break;
            case DIVIDE:
                if (numCalc2.intValue() == 0) {
                    break;
                }
                numCalc1 = numCalc1.divide(numCalc2);
                break;
        }
        num1 = numCalc1.toString();
        refreshScreen(true);
    }
}