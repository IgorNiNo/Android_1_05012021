package com.example.android_1_05012021;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListOfNoteFragment extends Fragment {

    public static final String ARG_NOTE = "argNote";
    private Notes note;

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы.
    public static ListOfNoteFragment newInstance(Notes note) {
        ListOfNoteFragment f = new ListOfNoteFragment(); // создание
        // Передача параметра
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Таким способом можно получить головной элемент из макета
        View view = inflater.inflate(R.layout.fragment_list_of_note, container, false);

        // найти в контейнере элемент - заголовок заметки
        AppCompatTextView nameListOfNote = view.findViewById(R.id.textName);
        // Установить значение в макет
        nameListOfNote.setText(note.getName());

        // найти в контейнере элемент - описание заметки
        AppCompatEditText descriptionListOfNote = view.findViewById(R.id.editTextDescription);
        // Установить значение в макет
        descriptionListOfNote.setText(note.getDescription());

        // найти в контейнере элемент - комментарий заметки
        AppCompatEditText commentListOfNote = view.findViewById(R.id.editTextComment);
        // Установить значение в макет
        commentListOfNote.setText(note.getComment());

        // найти в контейнере элемент - статус заметки
        AppCompatEditText statusListOfNote = view.findViewById(R.id.editTextStatus);
        // Установить значение в макет
        statusListOfNote.setText(note.getStatus());

        // найти в контейнере элемент - дата создания заметки
        AppCompatEditText dateOfCreationListOfNote = view.findViewById(R.id.editTextDateOfDescription);
        // Установить значение в макет
        dateOfCreationListOfNote.setText(note.getDateOfCreation());

        return view;
    }
}