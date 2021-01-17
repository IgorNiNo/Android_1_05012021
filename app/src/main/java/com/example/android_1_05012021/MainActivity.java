package com.example.android_1_05012021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private Double number1;
    private Double number2;
    private Double result;
    private int ariphmeticOperationCode;
    private int indexStartNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initButtonsClickListener();
    }

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
                inputDigit((String) button0.getText());
                break;
            case (R.id.button_1):
                inputDigit((String) button1.getText());
                break;
            case (R.id.button_2):
                inputDigit((String) button2.getText());
                break;
            case (R.id.button_3):
                inputDigit((String) button3.getText());
                break;
            case (R.id.button_4):
                inputDigit((String) button4.getText());
                break;
            case (R.id.button_5):
                inputDigit((String) button5.getText());
                break;
            case (R.id.button_6):
                inputDigit((String) button6.getText());
                break;
            case (R.id.button_7):
                inputDigit((String) button7.getText());
                break;
            case (R.id.button_8):
                inputDigit((String) button8.getText());
                break;
            case (R.id.button_9):
                inputDigit((String) button9.getText());
                break;
            case (R.id.button_comma):
                inputComma();
                break;
            case (R.id.button_signChange):
                inputSignChange();
                break;
            case (R.id.button_delete):
                deleteLastSimbol();
                break;
            case (R.id.button_esc):
                clearTextViewNumber();
                break;
            case (R.id.button_percent):
                inputOperation((String) buttonPercent.getText(), 1);
                break;
            case (R.id.button_plus):
                inputOperation((String) buttonPlus.getText(), 2);
                break;
            case (R.id.button_minus):
                inputOperation((String) buttonMinus.getText(), 3);
                break;
            case (R.id.button_multi):
                inputOperation((String) buttonMulti.getText(), 4);
                break;
            case (R.id.button_division):
                inputOperation((String) buttonDivision.getText(), 5);
                break;
            case (R.id.button_equal):
                getResult();
                break;
            default:
                break;
        }
    }

    // Добавить цифру на TextView
    private void inputDigit(String number) {
        if(searchSimbolEqual()){
            clearTextViewNumber();
        }
        String s = (String) textViewNumber.getText();
        textViewNumber.setText(String.format("%s%s", s, number));
    }

    // Ввод арифметических операций
    private void inputOperation(String operation, int codeOperation) {
        try {
            String s = (String) textViewNumber.getText();
            number1 = Double.valueOf(s);
            ariphmeticOperationCode = codeOperation;
            indexStartNum2 = s.length() + 1;
            textViewNumber.setText(String.format("%s%s", s, operation));
        } catch (NumberFormatException e) {
//            textViewNumber.setText("ERROR");
            clearTextViewNumber();
        }
    }

    // Считаем (нажали "=")
    private void getResult() {
        if(searchSimbolEqual()){
            clearTextViewNumber();
            return;
        }
        String s = (String) textViewNumber.getText();
        if(s.length()>0 && ariphmeticOperationCode!=0){
            String s1 = s.substring(indexStartNum2, s.length());
            number2 = Double.valueOf(s1);
            switch (ariphmeticOperationCode) {
                case 1: //Percent
                    result = number2 / 100.0 * number1;
                    break;
                case 2: //Plus
                    result = number1 + number2;
                    break;
                case 3: //Minus
                    result = number1 - number2;
                    break;
                case 4: //Multi
                    result = number1 * number2;
                    break;
                case 5: //Division
                    result = number1 / number2;
                    break;
                default:
                    break;
            }
        }
        if ((result % 1) != 0) {
            textViewNumber.setText(String.format(Locale.ROOT, "%s = %s", s, result));
        } else {
            textViewNumber.setText(String.format(Locale.ROOT, "%s = %.0f", s, result));
        }
    }

    // Установить знак десятичного разделителя
    private void inputComma() {
        if(searchSimbolEqual()){
            clearTextViewNumber();
        }
        String s = (String) textViewNumber.getText();
        if (indexStartNum2 == 0) {
            if (s.length() == 0) {
                textViewNumber.setText("0.");
            } else {
                int indexComma = s.indexOf(".");
                if (indexComma == -1) {
                    textViewNumber.setText(String.format("%s.", s));
                }
            }
        } else {
            String s1 = s.substring(0, indexStartNum2);
            String s2 = s.substring(indexStartNum2, s.length());
            if (s2.length() == 0) {
                textViewNumber.setText(String.format("%s0.", s1));
            } else {
                int indexComma = s2.indexOf(".");
                if (indexComma == -1) {
                    textViewNumber.setText(String.format("%s%s.", s1, s2));
                }
            }
        }
    }

    // Сменить знак числа (с положительного на отрицательное и наоборот)
    private void inputSignChange() {
        if(searchSimbolEqual()){
            clearTextViewNumber();
        }
        String s = (String) textViewNumber.getText();
        Double d1 = 0.0;
        if ((s.length() != 0)) {
            if (indexStartNum2 == 0) {
                d1 = -(Double.valueOf(s));
                if ((d1 % 1) != 0) {
                    textViewNumber.setText(String.format(Locale.ROOT, "%s", d1));
                } else {
                    textViewNumber.setText(String.format(Locale.ROOT, "%.0f", d1));
                }
//                Toast.makeText(getApplicationContext(), textViewNumber.getText(), Toast.LENGTH_SHORT).show();
            } else {
                String s1 = s.substring(0, indexStartNum2);
                String s2 = s.substring(indexStartNum2, s.length());
                if ((s2.length() != 0) && !s2.equals("-")) {
                    d1 = -(Double.valueOf(s2));
                    if ((d1 % 1) != 0) {
                        textViewNumber.setText(String.format(Locale.ROOT, "%s%s", s1, d1));
                    } else {
                        textViewNumber.setText(String.format(Locale.ROOT, "%s%.0f", s1, d1));
                    }
                }
            }
        }
    }

    // Удалить последний символ
    private void deleteLastSimbol() {
        String s = (String) textViewNumber.getText();
        if (s.length() > 1) {
            s = s.substring(0, s.length() - 1);
            textViewNumber.setText(String.format(Locale.ROOT, "%s", s));
            if (indexStartNum2 > s.length()) {
                indexStartNum2 = 0;
            }
        } else {
            textViewNumber.setText("");
            number1 = 0.0;
            number2 = 0.0;
            ariphmeticOperationCode = 0;
            indexStartNum2 = 0;
        }
    }

    // Очистить значение текста на TextView
    private void clearTextViewNumber() {
        textViewNumber.setText("");
        number1 = 0.0;
        number2 = 0.0;
        ariphmeticOperationCode = 0;
        indexStartNum2 = 0;
    }

    // Поиск знака "="
    private boolean searchSimbolEqual() {
        String s = (String) textViewNumber.getText();
        int indexEqual = s.indexOf("=");
        if (indexEqual != -1) {
            return true;
        } else {
            return false;
        }
    }

}