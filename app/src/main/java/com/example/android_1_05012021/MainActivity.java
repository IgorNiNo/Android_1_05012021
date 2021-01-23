package com.example.android_1_05012021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Calculator calculator = new Calculator();
    private final static String KEY_CALCULATOR = "KEY_CALCULATOR";
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonEsc;
    private Button buttonSignChange;
    private Button buttonPercent;
    private Button buttonDelete;
    private Button buttonComma;
    private Button buttonEqual;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMulti;
    private Button buttonDivision;
    private TextView textViewNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initButtonsClickListener();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_CALCULATOR, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable(KEY_CALCULATOR);
        textViewNumber.setText(calculator.getTextResult());
    }

    // Инициализация пользовательских элементов
    private void initViews() {
        //получим пользовательские элементы по идентификатору
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonEsc = findViewById(R.id.button_esc);
        buttonSignChange = findViewById(R.id.button_signChange);
        buttonPercent = findViewById(R.id.button_percent);
        buttonDelete = findViewById(R.id.button_delete);
        buttonComma = findViewById(R.id.button_comma);
        buttonEqual = findViewById(R.id.button_equal);
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        buttonMulti = findViewById(R.id.button_multi);
        buttonDivision = findViewById(R.id.button_division);
        textViewNumber = findViewById(R.id.textViewNumber);
    }

    // Устанавливаем слушателей на нажатие кнопок
    private void initButtonsClickListener() {
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonEsc.setOnClickListener(this);
        buttonSignChange.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonComma.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMulti.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        textViewNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.button_0):
                calculator.setInputSimbol((String) button0.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_1):
                calculator.setInputSimbol((String) button1.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_2):
                calculator.setInputSimbol((String) button2.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_3):
                calculator.setInputSimbol((String) button3.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_4):
                calculator.setInputSimbol((String) button4.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_5):
                calculator.setInputSimbol((String) button5.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_6):
                calculator.setInputSimbol((String) button6.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_7):
                calculator.setInputSimbol((String) button7.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_8):
                calculator.setInputSimbol((String) button8.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_9):
                calculator.setInputSimbol((String) button9.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_comma):
                calculator.setInputSimbol((String) buttonComma.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_signChange):
                calculator.setInputSimbol((String) buttonSignChange.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_delete):
                calculator.setInputSimbol("DEL");
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_esc):
                calculator.setInputSimbol((String) buttonEsc.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_percent):
                calculator.setInputSimbol((String) buttonPercent.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_plus):
                calculator.setInputSimbol((String) buttonPlus.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_minus):
                calculator.setInputSimbol((String) buttonMinus.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_multi):
                calculator.setInputSimbol((String) buttonMulti.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_division):
                calculator.setInputSimbol((String) buttonDivision.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            case (R.id.button_equal):
                calculator.setInputSimbol((String) buttonEqual.getText());
                textViewNumber.setText(calculator.getTextResult());
                break;
            default:
                break;
        }
    }
}