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

    private boolean isLandscape;
    protected static Notes[] notesView;

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

    // создаём список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        notesView = new Notes[]{
                new Notes(getString(R.string.note_1), getString(R.string.description_1), getString(R.string.comment_1), getString(R.string.status_1), new SimpleDateFormat("HH:mm:ss_dd/MM/yyyy").format(Calendar.getInstance().getTime())),
                new Notes(getString(R.string.note_2), getString(R.string.description_2), getString(R.string.comment_2), getString(R.string.status_2), new SimpleDateFormat("HH:mm:ss_dd/MM/yyyy").format(Calendar.getInstance().getTime())),
                new Notes(getString(R.string.note_3), getString(R.string.description_3), getString(R.string.comment_3), getString(R.string.status_3), new SimpleDateFormat("HH:mm:ss_dd/MM/yyyy").format(Calendar.getInstance().getTime()))
        };

        // В этом цикле создаём элемент TextView, заполняем его значениями и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for (int i = 0; i < notesView.length; i++) {
            String note = notesView[i].getName();
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showListOfNote(fi);
                }
            });
        }
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Определение, можно ли будет расположить рядом заметку в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        // Если можно нарисовать рядом заметку, то сделаем это
        if (isLandscape) {
            showLandListOfNote(0);
        }
    }

    private void showListOfNote(int index) {
        if (isLandscape) {
            showLandListOfNote(index);
        } else {
            showPortListOfNote(index);
        }
    }

    // Показать заметку в ландшафтной ориентации
    private void showLandListOfNote(int index) {
        // Создаём новый фрагмент с текущей позицией для вывода заметки
        ListOfNoteFragment detail = ListOfNoteFragment.newInstance(index);
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_list_of_note, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    // Показать заметку в портретной ориентации.
    private void showPortListOfNote(int index) {
        // Откроем вторую activity
        Intent intent = new Intent();
        intent.setClass(getActivity(), ListOfNoteActivity.class);
        // и передадим туда параметры
        intent.putExtra(ListOfNoteFragment.ARG_INDEX, index);
        startActivity(intent);
    }
}