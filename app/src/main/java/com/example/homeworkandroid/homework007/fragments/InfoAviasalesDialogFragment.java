package com.example.homeworkandroid.homework007.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework006.fragments.RequestDialogFragment;
import com.example.homeworkandroid.homework007.models.Aviasales;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class InfoAviasalesDialogFragment extends DialogFragment {

    // Фрагменты не могут напрямую взаимодействовать между собой.
    // Для этого надо обращаться к контексту, в качестве которого
    // выступает класс Activity. Для обращения к activity, как правило,
    // создается вложенный интерфейс
    public interface OnFragmentSendDataListener {
        void onSendData(int position) throws ParseException;
    } // OnFragmentSendDataListener

    // ссылка на активность, в которой будет запускаться диалог
    private OnFragmentSendDataListener activityRetranslator;
    private Activity activity;
    Integer select;
    Aviasales item;

    // при подключении к активности, context -  ссылка на активнсоть
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            activityRetranslator = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " должен реализовывать интерфейс OnFragmentSendDataListener");
        } // try-catch
    } // onAttach

    public InfoAviasalesDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // прочитать данные, переданные из активности (из точки вызова)
        // getArguments() - получить параметры из активности
        Bundle bundle = getArguments();

        // читаем строковый параметр
        this.select = bundle.getInt("select");
        this.item = bundle.getParcelable("item");

        activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder
                .setTitle(getResources().getString(R.string.info_aviasales))
                .setIcon(R.drawable.ic_hand_key_background)  // иконка из ресурсов нашего приложения
                .setView(R.layout.homework007_fragment_info_aviasales_dialog)       // добавить разметку - работает только с API21 и старше
        .setCancelable(true)//Чтобы пользователь не мог закрыть диалог нажатием в любой точке экрана
//                .setPositiveButton("ОК", onClickListener)   // для кнопок можно установить текст и обработчик
        // нажатия.
        // для кнопки "Отмена" обработчик установлен
//                .setNegativeButton("Отмена", onClickListener)
        ;

        return builder.create();
    } // onCreateDialog

    EditText edtLastName, edtFirstName, edtFlightNumber, edtDepartureDate, edtDestination;
    Button btnEdit, btnRemove, btnCancel;
    TextView txvPosition;

    Dialog dialog;

    private void setViews() {
        // получить ссылки на элементы интерфейса

        dialog = getDialog();
        edtLastName = (EditText) dialog.findViewById(R.id.edtLastName);
        edtFirstName = (EditText) dialog.findViewById(R.id.edtFirstName);
        edtFlightNumber = (EditText) dialog.findViewById(R.id.edtFlightNumber);
        edtDepartureDate = (EditText) dialog.findViewById(R.id.edtDepartureDate);
        edtDestination = (EditText) dialog.findViewById(R.id.edtDestination);

        btnEdit = (Button) dialog.findViewById(R.id.btnEdit);
        btnRemove = (Button) dialog.findViewById(R.id.btnRemove);
        btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        txvPosition = (TextView) dialog.findViewById(R.id.txvPosition);

        edtLastName.setText(this.item.getLastName());
        edtFirstName.setText(this.item.getFirstName());
        edtFlightNumber.setText(Integer.toString(this.item.getFlightNumber()));
        edtDepartureDate.setText(new SimpleDateFormat(Aviasales.date_pattern, Locale.getDefault()).format(this.item.getDepartureDate()));
        edtDestination.setText(this.item.getDestination());

        txvPosition.setText(Integer.toString(this.select));

        btnCancel.setOnClickListener(this::onButtonClickListener);
        btnEdit.setOnClickListener(this::onButtonClickListener);
        btnRemove.setOnClickListener(this::onButtonClickListener);
    }

    @SuppressLint("NonConstantResourceId")
    private void onButtonClickListener(View v) {
        switch (v.getId()) {

            case R.id.btnEdit:
                break;
            case R.id.btnRemove:
                try {
                    activityRetranslator.onSendData(this.select);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            case R.id.btnCancel:
                if (dialog != null) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                        dialog = null;
                    }
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // возможна работа с элементами разметки диалога
        // при помощи getDialog().findBiewById()
        setViews();

    }

    //    String parameter;
    // onClick
    // Слушатель события клик по кнопке диалога
    DialogInterface.OnClickListener onClickListener = (dialogInterface, id) -> {
        // определить состояние чек-бокса
//        Dialog thisDialog = getDialog();   // ссылка на разметку
//
//        // определить набранный в строке ввода текст
//        EditText editText = thisDialog.findViewById(R.id.editText);
//
//        parameter = editText.getText().toString();

        switch (id) {
            case Dialog.BUTTON_POSITIVE:

                // подготовить и показть Toast
                // getBaseContext() - ссылка на активность, в которой создан диалог
                Toast toast = Toast.makeText(activity.getBaseContext(), "", Toast.LENGTH_LONG);
                toast.setText("Запрос выполнен");
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                break;
            case Dialog.BUTTON_NEGATIVE:
                return;
        } // switch

    };
}