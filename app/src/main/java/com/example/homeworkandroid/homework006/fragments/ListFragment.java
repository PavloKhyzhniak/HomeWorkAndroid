package com.example.homeworkandroid.homework006.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework006.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;


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
    private OnFragmentSendDataListener activityRetranslator;
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
            activityRetranslator = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " должен реализовывать интерфейс OnFragmentSendDataListener");
        } // try-catch
    } // onAttach

    ListView countriesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.homework006_fragment_list, container, false);

        // получаем элемент ListView
        countriesList = view.findViewById(R.id.requestList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                requests);
        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);

        // добавляем для списка слушатель
        countriesList.setOnItemClickListener((parent, v, position, id) -> {
            this.position = position + 1;
            //в отдельном потоке
            //            new Thread(runnable).start();
            //через диалоги
            dialog(position+1);
        }
        );
        return view;
    } // onCreateViewUtils

    private void dialog(int position)
    {
        // создать диалог, передать ему строку - элемент списка
        RequestDialogFragment dialog = new RequestDialogFragment();

        // передача параметров в диалог - через Bundle
        Bundle args = new Bundle();    // объект для передачи параметров в диалог

        args.putInt("select",position);
        switch(position)
        {
            case 1:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_getAllUsers));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_getAllUsershint));
                break;
            case 2:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_getAllAddresses));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_getAllAddresseshint));
                break;
            case 3:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request01));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request01hint));
                break;
            case 4:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request02));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request02hint));
                break;
            case 5:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request03));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request03hint));
                break;
            case 6:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request04));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request04hint));
                break;
            case 7:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request05));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request05hint));
                break;
            case 8:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request06));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request06hint));
                break;
            case 9:
                args.putString("text",getResources().getString(R.string.HW006_exercises001_request07));
                args.putString("hint",getResources().getString(R.string.HW006_exercises001_request07hint));
                break;
        }
        // метод базового класса DialogFragment
        dialog.setArguments(args);

        // отображение диалогового окна
        dialog.show(((AppCompatActivity)activityRetranslator).getSupportFragmentManager(), "dialogRequest");
    }
    int position;
    private Runnable runnable = () -> {

        // для удобства восприятия
//            Utils.sleep(5_000);

        countriesList.post(() ->
                {
                    try {
                        // Посылаем данные Activity для ретрансляции в DatailFragment
                        activityRetranslator.onSendData(this.position);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
        );

    };
}