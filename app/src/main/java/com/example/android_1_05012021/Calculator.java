package com.example.android_1_05012021;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

class Calculator implements Parcelable {
    private String inputSimbol = "";
    private String textResult = "";
    private double number1;
    private double number2;
    private double result;
    private int ariphmeticOperationCode;
    private int indexStartNum2;

    public Calculator() {
        this.inputSimbol = "";
        this.textResult = "";
        this.number1 = 0.0;
        this.number2 = 0.0;
        this.result = 0.0;
        this.ariphmeticOperationCode = 0;
        this.indexStartNum2 = 0;
    }

    protected Calculator(Parcel in) {
        inputSimbol = in.readString();
        textResult = in.readString();
        number1 = in.readDouble();
        number2 = in.readDouble();
        result = in.readDouble();
        ariphmeticOperationCode = in.readInt();
        indexStartNum2 = in.readInt();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(inputSimbol);
        parcel.writeString(textResult);
        parcel.writeDouble(number1);
        parcel.writeDouble(number2);
        parcel.writeDouble(result);
        parcel.writeInt(ariphmeticOperationCode);
        parcel.writeInt(indexStartNum2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    void setInputSimbol(String simbol) {
        this.inputSimbol = simbol;
        analizeInputSimbol(inputSimbol);
    }

    String getTextResult() {
        return textResult;
    }

    // Анализ введенного символа
    private void analizeInputSimbol(String simbol) {
        switch (simbol) {
            case ("0"):
            case ("1"):
            case ("2"):
            case ("3"):
            case ("4"):
            case ("5"):
            case ("6"):
            case ("7"):
            case ("8"):
            case ("9"): // Нажата клавиша с цифрой от 0 до 9
                inputDigit(simbol);
                break;
            case ("%"): // Нажата клавиша "процент"
                inputOperation("%", 1);
                break;
            case ("+"): // Нажата клавиша "плюс"
                inputOperation("+", 2);
                break;
            case ("-"): // Нажата клавиша "минус"
                inputOperation("-", 3);
                break;
            case ("×"): // Нажата клавиша "умножение"
                inputOperation("×", 4);
                break;
            case ("÷"): // Нажата клавиша "деление"
                inputOperation("÷", 5);
                break;
            case ("."): // Нажата клавиша "десятичный разделитель"
                inputComma();
                break;
            case ("+/-"): // Нажата клавиша "смена знака числа"
                inputSignChange();
                break;
            case ("DEL"): // Нажата клавиша "удалить"
                deleteLastSimbol();
                break;
            case ("C"): // Нажата клавиша "удалить"
                clearTextResult();
                break;
            case ("="): // Нажата клавиша "равно"
                getResult();
                break;
            default:
                break;
        }
    }

    // Добавить цифру на TextView
    private void inputDigit(String number) {
        if (searchSimbolEqual()) {
            clearTextResult();
        }
        textResult = String.format("%s%s", textResult, number);
    }

    // Ввод арифметических операций
    private void inputOperation(String operation, int codeOperation) {
        try {
            number1 = Double.valueOf(textResult);
            ariphmeticOperationCode = codeOperation;
            indexStartNum2 = textResult.length() + 1;
            textResult = String.format("%s%s", textResult, operation);
        } catch (NumberFormatException e) {
            clearTextResult();
        }
    }

    // Получаем результат (нажали "=")
    private void getResult() {
        if (searchSimbolEqual()) {
            clearTextResult();
            return;
        }
        if (textResult.length() > 0 && ariphmeticOperationCode != 0) {
            String s1 = textResult.substring(indexStartNum2, textResult.length());
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
            textResult = String.format(Locale.ROOT, "%s = %s", textResult, result);
        } else {
            textResult = String.format(Locale.ROOT, "%s = %.0f", textResult, result);
        }
    }

    // Установить знак десятичного разделителя
    private void inputComma() {
        if (searchSimbolEqual()) {
            clearTextResult();
        }
        if (indexStartNum2 == 0) {
            if (textResult.length() == 0) {
                textResult = "0.";
            } else {
                int indexComma = textResult.indexOf(".");
                if (indexComma == -1) {
                    textResult = String.format("%s.", textResult);
                }
            }
        } else {
            String s1 = textResult.substring(0, indexStartNum2);
            String s2 = textResult.substring(indexStartNum2, textResult.length());
            if (s2.length() == 0) {
                textResult = String.format("%s0.", s1);
            } else {
                int indexComma = s2.indexOf(".");
                if (indexComma == -1) {
                    textResult = String.format("%s%s.", s1, s2);
                }
            }
        }
    }

    // Сменить знак числа (с положительного на отрицательное и наоборот)
    private void inputSignChange() {
        if (searchSimbolEqual()) {
            clearTextResult();
        }
        double d1 = 0.0;
        if ((textResult.length() != 0)) {
            if (indexStartNum2 == 0) {
                d1 = -(Double.valueOf(textResult));
                if ((d1 % 1) != 0) {
                    textResult = String.format(Locale.ROOT, "%s", d1);
                } else {
                    textResult = String.format(Locale.ROOT, "%.0f", d1);
                }
            } else {
                String s1 = textResult.substring(0, indexStartNum2);
                String s2 = textResult.substring(indexStartNum2, textResult.length());
                if ((s2.length() != 0) && !s2.equals("-")) {
                    d1 = -(Double.valueOf(s2));
                    if ((d1 % 1) != 0) {
                        textResult = String.format(Locale.ROOT, "%s%s", s1, d1);
                    } else {
                        textResult = String.format(Locale.ROOT, "%s%.0f", s1, d1);
                    }
                }
            }
        }
    }

    // Удалить последний символ
    private void deleteLastSimbol() {
        if (textResult.length() > 1) {
            textResult = textResult.substring(0, textResult.length() - 1);
            textResult = String.format(Locale.ROOT, "%s", textResult);
            if (indexStartNum2 > textResult.length()) {
                indexStartNum2 = 0;
            }
        } else {
            textResult = "";
            number1 = 0.0;
            number2 = 0.0;
            ariphmeticOperationCode = 0;
            indexStartNum2 = 0;
        }
    }

    // Очистить значение текста на TextView
    private void clearTextResult() {
        textResult = "";
        number1 = 0.0;
        number2 = 0.0;
        ariphmeticOperationCode = 0;
        indexStartNum2 = 0;
    }

    // Поиск знака "="
    private boolean searchSimbolEqual() {
        int indexEqual = textResult.indexOf("=");
        if (indexEqual != -1) {
            return true;
        } else {
            return false;
        }
    }
}
