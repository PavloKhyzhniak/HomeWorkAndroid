package com.example.homeworkandroid.homework006.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework006.db.DatabaseRepository_SQLiteHospital;
import com.example.homeworkandroid.homework006.fragments.DetailRequestFragment;
import com.example.homeworkandroid.homework006.fragments.ListFragment;
import com.example.homeworkandroid.homework006.fragments.RequestDialogFragment;
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

public class Exercises001 extends AppCompatActivity implements ListFragment.OnFragmentSendDataListener,
        RequestDialogFragment.OnFragmentSendDataListener,DetailRequestFragment.OnFragmentSendDataListener {

    private Button btnReturnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework006_activity_exercises001);

        findViews();

        setListeners();
    }

    // открытие БД
    @Override
    public void onResume() {
        super.onResume();

    } // onResume

    ArrayList<String> workWithRepository(int selectedItem) {
        // открыть базу данных
        DatabaseRepository_SQLiteHospital repository = new DatabaseRepository_SQLiteHospital(this);
        repository.open();
        ArrayList<String> collection = new ArrayList<>();
        try {
            switch ( selectedItem) {
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
                    List<PatientView> listPatientView = repository.request01("H%");
                    if (listPatientView != null) {
                        for (PatientView item : listPatientView) {
                            collection.add(item.toString());
                        }
                    }
                    break;
                case 4:
                    List<DoctorView> listDoctorView = repository.request02(2.3);
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
                    listDoctorView = repository.request04("T%");
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

    // реализация интерфейса передачи данных из одного фрагмента в другой
    // через активность
    public void onSendData(int selectedItem) throws ParseException {

//        ArrayList<String> collection = workWithRepositoryWithoutThread(selectedItem);
        ArrayList<String> collection = workWithRepository(selectedItem);

        this.onSendData(collection);

    } // onSendData

    @Override
    public void onSendData(ArrayList<String> collection) throws ParseException {
        // если ориентация горизонтальная, передаем данные фрагменту
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            // получаем ссылку на фрагмент-приемник
            DetailRequestFragment fragment = (DetailRequestFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frDetails);

            assert fragment != null;
            fragment.setCollection(collection);


        } else {
            // ориентация вертикальная, вызываем
            // активность, в которой и размещен целевой фрагмент
            Intent intent = new Intent(this, DetailRequestActivity.class);
            intent.putStringArrayListExtra(DetailRequestActivity.REQUEST_DATAS, collection);
            startActivity(intent);
        } // if
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

    }

    private void setListeners() {
        btnReturnToMain.setOnClickListener(v -> returnToMain());

    }


    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(myIntent);
        finish();
    }

    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.back_menu, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu


    // обработчик события выбора в меню
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniBack:
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected

    @Override
    public void onSendData(String data) {

    }
    // endregion
}