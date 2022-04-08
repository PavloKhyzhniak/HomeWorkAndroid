package com.example.homeworkandroid.homework006.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.homeworkandroid.R;

import java.util.ArrayList;


public class DetailRequestFragment extends Fragment {

    public DetailRequestFragment() {
        // Required empty public constructor
    }

    // Фрагменты не могут напрямую взаимодействовать между собой.
    // Для этого надо обращаться к контексту, в качестве которого
    // выступает класс Activity. Для обращения к activity, как правило,
    // создается вложенный интерфейс
    public interface OnFragmentSendDataListener {
        void onSendData(String data);
    } // OnFragmentSendDataListener

    // ссылка на активность, в которой находится фрагмент
    private OnFragmentSendDataListener activivtyRetranslator;

    // при подключении к активности, context -  ссылка на активнсоть
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activivtyRetranslator = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        } // try-catch
    } // onAttach

    String[] collection = {
    };

    public void setCollection(ArrayList<String> array) {
        collection = new String[array.size()];
        int index = 0;
        for (String item : array) {
            collection[index] = item;
            index++;
        }
        if (view != null)
            refreshListView();
    }

    void refreshListView() {
        // получаем элемент ListView
        ListView requestList = view.findViewById(R.id.dataList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                collection);
        // устанавливаем для списка адаптер
        requestList.setAdapter(adapter);

        // добавляем для списка слушатель
        requestList.setOnItemClickListener((parent, v, position, id) -> {
            // получаем выбранный элемент
            String selectedItem = (String) parent.getItemAtPosition(position);
            // Посылаем данные Activity для ретрансляции в DatailFragment
            activivtyRetranslator.onSendData(selectedItem);
        });
    }

    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.homework006_fragment_detail_request, container, false);

        refreshListView();

        return view;
    }
}