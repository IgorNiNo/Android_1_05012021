package com.example.android_1_05012021;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListOfNoteFragment extends Fragment {

    public static final String ARG_INDEX = "ARG_INDEX";
    private int index;

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы.
    public static ListOfNoteFragment newInstance(int index) {
        ListOfNoteFragment f = new ListOfNoteFragment(); // создание
        // Передача параметра
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Таким способом можно получить головной элемент из макета
        View view = inflater.inflate(R.layout.fragment_list_of_note, container, false);
        // найти в контейнере элемент-заголовок заметки
        AppCompatTextView nameListOfNote = view.findViewById(R.id.textName);
        // Получить из ресурсов массив указателей на заголовки заметок
        TypedArray name = getResources().obtainTypedArray(R.array.name);
        // Выбрать по индексу подходящий
        nameListOfNote.setText(name.getResourceId(index, -1));

        AppCompatEditText descriptionListOfNote = view.findViewById(R.id.editTextDescription);
        // Получить из ресурсов массив указателей на описание заметок
        TypedArray description = getResources().obtainTypedArray(R.array.description);
        // Выбрать по индексу подходящий
        descriptionListOfNote.setText(description.getResourceId(index, -1));

        AppCompatEditText commentListOfNote = view.findViewById(R.id.editTextComment);
        // Получить из ресурсов массив указателей на комментарий заметок
        TypedArray comment = getResources().obtainTypedArray(R.array.comment);
        // Выбрать по индексу подходящий
        commentListOfNote.setText(comment.getResourceId(index, -1));

        AppCompatEditText statusListOfNote = view.findViewById(R.id.editTextStatus);
        // Получить из ресурсов массив указателей на статус заметок
        TypedArray status = getResources().obtainTypedArray(R.array.status);
        // Выбрать по индексу подходящий
        statusListOfNote.setText(status.getResourceId(index, -1));

        AppCompatEditText dateOfCreationListOfNote = view.findViewById(R.id.editTextDateOfDescription);
        // Получить из массива время создания заметок
        // Выбрать по индексу подходящий
        dateOfCreationListOfNote.setText(NotesFragment.notesView[index].getDateOfCreation());

        return view;
    }
}