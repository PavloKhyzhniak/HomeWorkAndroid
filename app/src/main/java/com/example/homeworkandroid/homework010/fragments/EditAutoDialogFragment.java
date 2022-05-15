package com.example.homeworkandroid.homework010.fragments;

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
import com.example.homeworkandroid.homework010.models.Auto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditAutoDialogFragment extends DialogFragment {

    // Фрагменты не могут напрямую взаимодействовать между собой.
    // Для этого надо обращаться к контексту, в качестве которого
    // выступает класс Activity. Для обращения к activity, как правило,
    // создается вложенный интерфейс
    public interface OnFragmentEditAutoSendDataListener {
        void onSendData(int position,Auto item) throws ParseException;
        void onRemoveElement(int position) throws ParseException;
    } // OnFragmentSendDataListener

    // ссылка на активность, в которой будет запускаться диалог
    private OnFragmentEditAutoSendDataListener activityRetranslator;
    private Activity activity;
    Integer select;
    Auto item;

    // при подключении к активности, context -  ссылка на активнсоть
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            activityRetranslator = (OnFragmentEditAutoSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " должен реализовывать интерфейс OnFragmentSendDataListener");
        } // try-catch
    } // onAttach

    public EditAutoDialogFragment() {
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
                .setTitle(getResources().getString(R.string.edit_auto))
                .setIcon(R.drawable.edit)  // иконка из ресурсов нашего приложения
                .setView(R.layout.homework010_fragment_edit_auto_dialog)       // добавить разметку - работает только с API21 и старше
//                .setPositiveButton("ОК", onClickListener)   // для кнопок можно установить текст и обработчик
        // нажатия.
        // для кнопки "Отмена" обработчик установлен
//                .setNegativeButton("Отмена", onClickListener)
        ;

        return builder.create();
    } // onCreateDialog


    EditText edtBrandName, edtModelName, edtYearOfManufacture, edtEnginePower, edtPrice, edtSymbolicImage;
    Button btnSave, btnDelete, btnCancel;

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

        edtBrandName = (EditText) dialog.findViewById(R.id.edtBrandName);
        edtModelName = (EditText) dialog.findViewById(R.id.edtModelName);
        edtYearOfManufacture = (EditText) dialog.findViewById(R.id.edtYearOfManufacture);
        edtEnginePower = (EditText) dialog.findViewById(R.id.edtEnginePower);
        edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        edtSymbolicImage = (EditText) dialog.findViewById(R.id.edtSymbolicImage);

        btnSave = (Button) dialog.findViewById(R.id.btnSave);
        btnDelete = (Button) dialog.findViewById(R.id.btnDelete);
        btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        edtBrandName.setText(this.item.getBrandName());
        edtModelName.setText(this.item.getModelName());
        edtYearOfManufacture.setText(String.format(Locale.UK, "%d", item.getYearOfManufacture()));
        edtEnginePower.setText(String.format(Locale.UK, "%.2f", item.getEnginePower()));
        edtPrice.setText(String.format(Locale.UK, "%d", item.getPrice()));
//        edtYearOfManufacture.setText(Integer.toString(this.item.getYearOfManufacture()));
//        edtEnginePower.setText(Double.toString(this.item.getEnginePower()));
//        edtPrice.setText(Integer.toString(this.item.getPrice()));
        edtSymbolicImage.setText(this.item.getSymbolicImage());

        btnCancel.setOnClickListener(this::onButtonClickListener);
        btnDelete.setOnClickListener(this::onButtonClickListener);
        btnSave.setOnClickListener(this::onButtonClickListener);
    }

    @SuppressLint("NonConstantResourceId")
    private void onButtonClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                try {
                    refreshItem();
                    activityRetranslator.onSendData(this.select,this.item);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (dialog != null) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                        dialog = null;
                    }
                }
                break;
            case R.id.btnDelete:
                try {
                    activityRetranslator.onRemoveElement(this.select);
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
        this.item.setBrandName(edtBrandName.getText().toString());
        this.item.setModelName(edtModelName.getText().toString());
        this.item.setYearOfManufacture(Integer.parseInt(edtYearOfManufacture.getText().toString()));
        this.item.setEnginePower(Double.parseDouble(edtEnginePower.getText().toString()));
        this.item.setPrice(Integer.parseInt(edtPrice.getText().toString()));
        this.item.setSymbolicImage(edtSymbolicImage.getText().toString());

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
        } // switch

    };
}