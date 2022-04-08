package com.example.homeworkandroid.homework006.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework006.utils.Utils;

import java.text.ParseException;


public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }

    // Фрагменты не могут напрямую взаимодействовать между собой.
    // Для этого надо обращаться к контексту, в качестве которого
    // выступает класс Activity. Для обращения к activity, как правило,
    // создается вложенный интерфейс
    public interface OnFragmentSendDataListener {
        void onSendData(int data) throws ParseException;
    } // OnFragmentSendDataListener

    // ссылка на активность, в которой находится фрагмент
    private OnFragmentSendDataListener activivtyRetranslator;
    String[] requests;


    // при подключении к активности, context -  ссылка на активнсоть
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        requests = new String[]{
                getResources().getString(R.string.HW006_exercises001_getAllUsers),
                getResources().getString(R.string.HW006_exercises001_getAllAddresses),
                getResources().getString(R.string.HW006_exercises001_request01),
                getResources().getString(R.string.HW006_exercises001_request02),
                getResources().getString(R.string.HW006_exercises001_request03),
                getResources().getString(R.string.HW006_exercises001_request04),
                getResources().getString(R.string.HW006_exercises001_request05),
                getResources().getString(R.string.HW006_exercises001_request06),
                getResources().getString(R.string.HW006_exercises001_request07),
        };

        try {
            activivtyRetranslator = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        } // try-catch
    } // onAttach

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.homework006_fragment_list, container, false);

        // получаем элемент ListView
        ListView countriesList = view.findViewById(R.id.requestList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                requests);
        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);

        // добавляем для списка слушатель
        countriesList.setOnItemClickListener((parent, v, position, id) -> {
            // Посылаем данные Activity для ретрансляции в DatailFragment
            this.position = position;
            new Thread(runnable).start();
        });
        return view;
    } // onCreateViewUtils

    int position;
    private Runnable runnable = () -> {
        try {
            // для удобства восприятия
            Utils.sleep(5_000);
            activivtyRetranslator.onSendData(this.position);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    };
}