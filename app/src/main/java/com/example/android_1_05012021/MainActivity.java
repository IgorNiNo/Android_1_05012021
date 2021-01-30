package com.example.android_1_05012021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         if (savedInstanceState == null) {
             // Если эта activity запускается первый раз (с каждой новой заметкой первый раз),
             // то перенаправим параметр фрагменту
             NotesFragment details = new NotesFragment();
             details.setArguments(getIntent().getExtras());
             // Добавим фрагмент на activity
             getSupportFragmentManager()
                     .beginTransaction()
                     .replace(R.id.fragment_container, details).commit();
         }
     }
}