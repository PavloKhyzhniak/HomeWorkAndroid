package com.example.homeworkandroid.homework006.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework006.db.DatabaseRepository_SQLiteHospital;
import com.example.homeworkandroid.homework006.models.Address;
import com.example.homeworkandroid.homework006.models.User;
import com.example.homeworkandroid.homework006.modelview.DoctorPriceView;
import com.example.homeworkandroid.homework006.modelview.DoctorView;
import com.example.homeworkandroid.homework006.modelview.PatientView;
import com.example.homeworkandroid.homework006.modelview.SpecializationAvgRateView;
import com.example.homeworkandroid.homework006.modelview.VisitMaxPriceView;
import com.example.homeworkandroid.homework006.modelview.VisitView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RequestDialogFragment extends DialogFragment {

    // Фрагменты не могут напрямую взаимодействовать между собой.
    // Для этого надо обращаться к контексту, в качестве которого
    // выступает класс Activity. Для обращения к activity, как правило,
    // создается вложенный интерфейс
    public interface OnFragmentSendDataListener {
        void onSendData(ArrayList<String> collection) throws ParseException;
    } // OnFragmentSendDataListener

    // ссылка на активность, в которой будет запускаться диалог
    private OnFragmentSendDataListener activityRetranslator;
    private Activity activity;
    Integer select;
    String text;
    String hint;

    // при подключении к активности, context -  ссылка на активнсоть
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            activityRetranslator = (RequestDialogFragment.OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " должен реализовывать интерфейс OnFragmentSendDataListener");
        } // try-catch
    } // onAttach

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // прочитать данные, переданные из активности (из точки вызова)
        // getArguments() - получить параметры из активности
        Bundle bundle = getArguments();

        // читаем строковый параметр
        this.text = bundle.getString("text");
        this.hint = bundle.getString("hint");
        this.select = bundle.getInt("select");


        activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder
                .setTitle("Запрос")
                .setIcon(R.drawable.ic_hand_key_background)  // иконка из ресурсов нашего приложения
                .setView(R.layout.homework006_fragment_request_dialog)       // добавить разметку - работает только с API21 и старше
                .setPositiveButton("ОК", onClickListener)   // для кнопок можно установить текст и обработчик
                // нажатия.
                // для кнопки "Отмена" обработчик установлен
                .setNegativeButton("Отмена", onClickListener);

        return builder.create();
    } // onCreateDialog

    TextView txvText;

    @Override
    public void onStart() {
        super.onStart();
        // возможна работа с элементами разметки диалога
        // при помощи getDialog().findBiewById()
        txvText = (TextView) getDialog().findViewById(R.id.txvText);
        txvText.setText(this.text);
        ((EditText) getDialog().findViewById(R.id.editText)).setHint(this.hint);

        switch (select) {
            case 3:
            case 4:
            case 6:
                break;
            default:
                getDialog().findViewById(R.id.editText).setVisibility(View.GONE);
        }
    }

    String parameter;
    // onClick
    // Слушатель события клик по кнопке диалога
    DialogInterface.OnClickListener onClickListener = (dialogInterface, id) -> {
        // определить состояние чек-бокса
        Dialog thisDialog = getDialog();   // ссылка на разметку

        // определить набранный в строке ввода текст
        EditText editText = thisDialog.findViewById(R.id.editText);

        parameter = editText.getText().toString();
        if (this.select == 3 || this.select == 4 || this.select == 6)
            if (parameter.isEmpty()) {
                // подготовить и показть Toast
                // getBaseContext() - ссылка на активность, в которой создан диалог
                Toast toast = Toast.makeText(activity.getBaseContext(), "", Toast.LENGTH_LONG);
                toast.setText("Введите параметер");
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                return;
            }

        switch (id) {
            case Dialog.BUTTON_POSITIVE:
                runnable_req();

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

    private void runnable_req() {
        try {
            activityRetranslator.onSendData(
                    workWithRepository(this.select, this.parameter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    ArrayList<String> workWithRepository(int selectedItem, String parameter) {
        // открыть базу данных
        DatabaseRepository_SQLiteHospital repository = new DatabaseRepository_SQLiteHospital(activity);
        repository.open();
        ArrayList<String> collection = new ArrayList<>();
        try {
            switch (selectedItem) {
                case 1:
                    List<User> list = repository.getUsers();
                    if (list != null) {
                        for (User item : list) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 2:
                    List<Address> listAddresses = repository.getAddresses();
                    if (listAddresses != null) {
                        for (Address item : listAddresses) {
                            collection.add(item.toString());
                        }
                    }
//                        collection = (String[]) repository.request01("K").toArray();
                    break;
                case 3:
                    List<PatientView> listPatientView = repository.request01(parameter);
                    if (listPatientView != null) {
                        for (PatientView item : listPatientView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 4:
                    List<DoctorView> listDoctorView = repository.request02(Double.parseDouble(parameter));
                    if (listDoctorView != null) {
                        for (DoctorView item : listDoctorView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 5:
                    List<VisitView> listVisitView = repository.request03();
                    if (listVisitView != null) {
                        for (VisitView item : listVisitView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 6:
                    listDoctorView = repository.request04(parameter);
                    if (listDoctorView != null) {
                        for (DoctorView item : listDoctorView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 7:
                    List<DoctorPriceView> listDoctorPriceView = repository.request05();
                    if (listDoctorPriceView != null) {
                        for (DoctorPriceView item : listDoctorPriceView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 8:
                    List<VisitMaxPriceView> listVisitMaxPriceView = repository.request06();
                    if (listVisitMaxPriceView != null) {
                        for (VisitMaxPriceView item : listVisitMaxPriceView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 9:
                    List<SpecializationAvgRateView> listSpecializationAvgRateView = repository.request07();
                    if (listSpecializationAvgRateView != null) {
                        for (SpecializationAvgRateView item : listSpecializationAvgRateView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.close();
        return collection;
    }
}