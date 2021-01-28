package com.example.android_1_05012021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

// Эта activity для показа заметки в портретной ориентации
public class ListOfNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_note);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Если эта activity запускается первый раз (с каждым новой заметкой первый раз),
            // то перенаправим параметр фрагменту
            ListOfNoteFragment details = new ListOfNoteFragment();
            details.setArguments(getIntent().getExtras());
            // Добавим фрагмент на activity
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, details).commit();
        }
    }
}