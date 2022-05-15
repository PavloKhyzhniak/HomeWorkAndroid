package com.example.homeworkandroid.homework010.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework004.models.TVShop;
import com.example.homeworkandroid.homework007.fragments.EditAviasalesDialogFragment;
import com.example.homeworkandroid.homework007.fragments.InfoAviasalesDialogFragment;
import com.example.homeworkandroid.homework010.adapters.AutoAdapter;
import com.example.homeworkandroid.homework010.fragments.EditAutoDialogFragment;
import com.example.homeworkandroid.homework010.models.Auto;
import com.example.homeworkandroid.homework010.models.CollectionAuto;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;

public class Exercises001 extends AppCompatActivity
        implements EditAutoDialogFragment.OnFragmentEditAutoSendDataListener{

    final String LOG_TAG = "myLogs";

    private Button btnReturnToMain;

    private Button btnNewCollection,btnSortDescPrice,btnSortAscPrice,btnSortBrandModel;

    // данные для сохранения/загрузки
    private CollectionAuto collection;

    // элемент отображения списка
    ListView lsvItemsList;
    ArrayAdapter<Auto> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework010_activity_exercises001);

        // создали коллекцию товаров для обработки, при создании
        // выполняется заполнение коллекции начальными значениями
        collection = new CollectionAuto();

        findViews();

        setListeners();

        refreshAdapter();
    } // onCreate

    private void refreshAdapter() {
//        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                collection.getItemsList());
        itemsAdapter = new AutoAdapter(this, R.layout.homework010_auto_item,
                collection.getItemsList());

        // устанавливаем для списка адаптер
        lsvItemsList.setAdapter(itemsAdapter);
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnNewCollection = findViewById(R.id.btnNewCollection);
        btnSortDescPrice = findViewById(R.id.btnSortDescPrice);
        btnSortAscPrice = findViewById(R.id.btnSortAscPrice);
        btnSortBrandModel = findViewById(R.id.btnSortBrandModel);

        lsvItemsList = findViewById(R.id.lsvItemsList);
    }

    private void setListeners() {
        btnReturnToMain.setOnClickListener(this::OnClickListener);
        btnNewCollection.setOnClickListener(this::OnClickListener);
        btnSortDescPrice.setOnClickListener(this::OnClickListener);
        btnSortAscPrice.setOnClickListener(this::OnClickListener);
        btnSortBrandModel.setOnClickListener(this::OnClickListener);

    }

    @SuppressLint("NonConstantResourceId")
    private void OnClickListener(View v) {

        switch (v.getId()) {
            case R.id.btnReturnToMain:
                Intent myIntent = new Intent(this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                finish();
                break;
            case R.id.btnNewCollection:
                collection = new CollectionAuto();

                refreshAdapter();
                break;
            case R.id.btnSortDescPrice:
                Collections.sort(collection.getItemsList(), (Comparator<Auto>) (lhs, rhs) -> {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return Integer.compare(rhs.getPrice(), lhs.getPrice());
                });

                refreshAdapter();
                break;
            case R.id.btnSortAscPrice:
                Collections.sort(collection.getItemsList(), (Comparator<Auto>) (lhs, rhs) -> {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return Integer.compare(lhs.getPrice(), rhs.getPrice());
                });

                refreshAdapter();
                break;
            case R.id.btnSortBrandModel:
                Collections.sort(collection.getItemsList(), (Comparator<Auto>) (lhs, rhs) -> {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    String obj1 = lhs.getBrandName();
                    String obj2 = rhs.getBrandName();
                    if (obj1.equals(obj2)) {
                        return 0;
                    }
                    if (obj1 == null) {
                        return -1;
                    }
                    if (obj2 == null) {
                        return 1;
                    }
                    return obj1.compareTo(obj2);
                });

                Collections.sort(collection.getItemsList(), (Comparator<Auto>) (lhs, rhs) -> {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    String obj1 = lhs.getModelName();
                    String obj2 = rhs.getModelName();
                    if (obj1.equals(obj2)) {
                        return 0;
                    }
                    if (obj1 == null) {
                        return -1;
                    }
                    if (obj2 == null) {
                        return 1;
                    }
                    return obj1.compareTo(obj2);
                });
                refreshAdapter();
                break;

        }
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
    public void onSendData(int position, Auto item) throws ParseException {
        //refresh old item
        collection.getItemsList().set(position, item);
    }

    @Override
    public void onRemoveElement(int position) throws ParseException {
        collection.getItemsList().remove(position);
    }
}