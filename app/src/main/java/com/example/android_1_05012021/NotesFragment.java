package com.example.android_1_05012021;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotesFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Notes currentNote;
    private boolean isLandscape;

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // создаём список заметок на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.name);

        // В этом цикле создаём элемент TextView, заполняем его значениями и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNote = new Notes(getResources().getStringArray(R.array.name)[fi],
                            getResources().getStringArray(R.array.description)[fi],
                            getResources().getStringArray(R.array.comment)[fi],
                            getResources().getStringArray(R.array.status)[fi],
                            new SimpleDateFormat("HH:mm:ss  dd/MM/yyyy").format(Calendar.getInstance().getTime()));
                    showListOfNote(currentNote);
                }
            });
        }
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Определение, можно ли будет расположить рядом заметку в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            // Если восстановить не удалось, то сделаем объект с первым индексом
            currentNote = new Notes(getResources().getStringArray(R.array.name)[0],
                    getResources().getStringArray(R.array.description)[0],
                    getResources().getStringArray(R.array.comment)[0],
                    getResources().getStringArray(R.array.status)[0],
                    new SimpleDateFormat("HH:mm:ss  dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        }
        // Если можно нарисовать рядом заметку, то сделаем это
        if (isLandscape) {
            showLandListOfNote(currentNote);
        }
    }

    private void showListOfNote(Notes currentNote) {
        if (isLandscape) {
            showLandListOfNote(currentNote);
        } else {
            showPortListOfNote(currentNote);
        }
    }

    // Показать заметку в ландшафтной ориентации
    private void showLandListOfNote(Notes currentNote) {
        // Создаём новый фрагмент с текущей позицией для вывода заметки
        ListOfNoteFragment detail = ListOfNoteFragment.newInstance(currentNote);
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_list_of_note, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    // Показать заметку в портретной ориентации.
    private void showPortListOfNote(Notes currentNote) {
        // Откроем вторую activity
        Intent intent = new Intent();
        intent.setClass(getActivity(), ListOfNoteActivity.class);
        // и передадим туда параметры
        intent.putExtra(ListOfNoteFragment.ARG_NOTE, currentNote);
        startActivity(intent);
    }
}