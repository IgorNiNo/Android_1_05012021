package com.example.android_1_05012021;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

abstract class BaseActivity extends AppCompatActivity {

    // Имя настроек
    protected static final String APP_THEME_PREFERENCE = "APP_THEME_PREFERENCE";
    // Имя параметра в настройках
    protected static final String APP_THEME = "APP_THEME";

    protected static final int THEME_LIGHT = 0;
    protected static final int THEME_DARK = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливать тему надо только до установки макета активити
        setTheme(getAppTheme(R.style.AppTheme));
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    // Чтение настроек, параметр тема
    protected int getCodeStyle(int codeStyle) {
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(APP_THEME_PREFERENCE, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(APP_THEME, codeStyle);
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
//            case THEME_LIGHT:
//                return R.style.AppTheme;
            case THEME_DARK:
                return R.style.AppThemeDark;
            default:
                return R.style.AppTheme;
        }
    }

    // Сохранение настроек
    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(APP_THEME_PREFERENCE, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }
}
