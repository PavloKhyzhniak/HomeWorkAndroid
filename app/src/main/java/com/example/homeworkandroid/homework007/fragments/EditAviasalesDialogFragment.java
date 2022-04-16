package com.example.homeworkandroid.homework007.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.models.Aviasales;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditAviasalesDialogFragment extends DialogFragment {

    // Фрагменты не могут напрямую взаимодействовать между собой.
    // Для этого надо обращаться к контексту, в качестве которого
    // выступает класс Activity. Для обращения к activity, как правило,
    // создается вложенный интерфейс
    public interface OnFragmentSendDataListener {
        void onSendData(int position,Aviasales item) throws ParseException;
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

    public EditAviasalesDialogFragment() {
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
                .setTitle(getResources().getString(R.string.edit_aviasales))
                .setIcon(R.drawable.ic_hand_key_background)  // иконка из ресурсов нашего приложения
                .setView(R.layout.homework007_fragment_edit_aviasales_dialog)       // добавить разметку - работает только с API21 и старше
//                .setPositiveButton("ОК", onClickListener)   // для кнопок можно установить текст и обработчик
        // нажатия.
        // для кнопки "Отмена" обработчик установлен
//                .setNegativeButton("Отмена", onClickListener)
        ;

        return builder.create();
    } // onCreateDialog

    EditText edtLastName, edtFirstName, edtFlightNumber, edtDepartureDate, edtDestination;
    Button btnSave, btnCreate, btnCancel;
    TextView txvPosition;

    Dialog dialog;


    @Override
    public void onStart() {
        super.onStart();
        // возможна работа с элементами разметки диалога
        // при помощи getDialog().findBiewById()
        setViews();

    }

    @SuppressLint("SetTextI18n")
    private void setViews() {
        // получить ссылки на элементы интерфейса

        dialog = getDialog();
        edtLastName = (EditText) dialog.findViewById(R.id.edtLastName);
        edtFirstName = (EditText) dialog.findViewById(R.id.edtFirstName);
        edtFlightNumber = (EditText) dialog.findViewById(R.id.edtFlightNumber);
        edtDepartureDate = (EditText) dialog.findViewById(R.id.edtDepartureDate);
        edtDestination = (EditText) dialog.findViewById(R.id.edtDestination);

        btnSave = (Button) dialog.findViewById(R.id.btnSave);
        btnCreate = (Button) dialog.findViewById(R.id.btnCreate);
        btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        txvPosition = (TextView) dialog.findViewById(R.id.txvPosition);

        edtLastName.setText(this.item.getLastName());
        edtFirstName.setText(this.item.getFirstName());
        edtFlightNumber.setText(Integer.toString(this.item.getFlightNumber()));
        edtDepartureDate.setText(new SimpleDateFormat(Aviasales.date_pattern, Locale.getDefault()).format(this.item.getDepartureDate()));
        edtDestination.setText(this.item.getDestination());

        txvPosition.setText(Integer.toString(this.select));

        btnCancel.setOnClickListener(this::onButtonClickListener);
        btnSave.setOnClickListener(this::onButtonClickListener);
        btnCreate.setOnClickListener(this::onButtonClickListener);
        edtDepartureDate.setOnClickListener(this::onButtonClickListener);
    }

    @SuppressLint("NonConstantResourceId")
    private void onButtonClickListener(View v) {
        switch (v.getId()) {
            case R.id.edtDepartureDate:
                new DatePickerDialog(
                        activity,                  // контекст создания окна
                        dateSetListener,                    // слушатель события - дата изменена
                        dateAndTime.get(Calendar.YEAR),     // задать год, месяц, и день из объекта-календаря
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();  // показать диалог
                new TimePickerDialog(
                        activity,                      // контекст создания диалогового окна
                        timeSetListener,                        // слушатель события изменение времени в диалоге
                        dateAndTime.get(Calendar.HOUR_OF_DAY),  // час
                        dateAndTime.get(Calendar.MINUTE),       // минута
                        true)                                   // 24-х часовый формат времени
                        .show();

                String currentDate = new SimpleDateFormat(Aviasales.date_pattern, Locale.getDefault()).format(dateAndTime);
                edtDepartureDate.setText(currentDate);
                break;
            case R.id.btnCreate:
                this.select = -1;
                this.item = new Aviasales();
            case R.id.btnSave:
                try {
                    refreshItem();
                    activityRetranslator.onSendData(this.select,this.item);
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

    private void refreshItem() throws ParseException {
        this.item.setDestination(edtDestination.getText().toString());
        this.item.setLastName(edtLastName.getText().toString());
        this.item.setFirstName(edtFirstName.getText().toString());
        this.item.setFlightNumber(Integer.parseInt(edtFlightNumber.getText().toString()));

        DateFormat formatter = new SimpleDateFormat(Aviasales.date_pattern, Locale.getDefault());
        this.item.setDepartureDate((Date) formatter.parse(edtDepartureDate.getText().toString()));
    }

    // объект для работы с датой и временем
    Calendar dateAndTime = Calendar.getInstance();
    // установка обработчика изменения/выбора времени
    TimePickerDialog.OnTimeSetListener timeSetListener = (TimePicker view, int hourOfDay, int minute)
            -> {
        // подготовить и вывести новое время в строке отображения
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
    };
    // установка обработчика изменения/выбора даты
    private final DatePickerDialog.OnDateSetListener dateSetListener =
            (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
                // подготовить и вывести новую дату в строке отображения
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            };


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
        } // switch

    };
}