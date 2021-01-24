package com.example.android_1_05012021;

import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;

public class SettingsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private SwitchCompat switchTheme;
    private static boolean isChangeTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initThemeChooser();

        Button btnReturn = findViewById(R.id.button_return);
        btnReturn.setOnClickListener(view -> {
            if (isChangeTheme) {
                setResult(RESULT_OK);
            } else {
                setResult(RESULT_CANCELED);
            }
            isChangeTheme = false;
            // Метод finish() завершает активити
            finish();
        });
    }

    private void initThemeChooser() {
        switchTheme = findViewById(R.id.switch_theme);
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(APP_THEME_PREFERENCE, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        int codeTheme = sharedPref.getInt(APP_THEME, THEME_LIGHT);
        if (codeTheme == THEME_LIGHT) {
            switchTheme.setChecked(false);
        } else {
            switchTheme.setChecked(true);
        }
        if (switchTheme != null) {
            switchTheme.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            setAppTheme(THEME_DARK);
        } else {
            setAppTheme(THEME_LIGHT);
        }
        isChangeTheme = true;
        recreate();
    }
}