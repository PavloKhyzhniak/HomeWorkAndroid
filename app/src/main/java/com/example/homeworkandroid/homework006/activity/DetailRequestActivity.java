package com.example.homeworkandroid.homework006.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework006.fragments.DetailRequestFragment;

import java.util.ArrayList;

public class DetailRequestActivity extends AppCompatActivity implements DetailRequestFragment.OnFragmentSendDataListener{

    public static final String REQUEST_DATAS = "REQUEST_DATAS";
    ArrayList<String> collection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // при горизонтальной ориентации - выход, для корректного отображения - при
        // помощи MainActivity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        } // if

        setContentView(R.layout.homework006_activity_detail_request);

        // получение даннных для фрагмента
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            collection = extras.getStringArrayList(REQUEST_DATAS);
    }

    // перед началом работы активности, в момент когда фрагмент гарантированно готов к работе,
    // передать данные фрагменту
    @Override
    protected void onResume() {
        super.onResume();
        DetailRequestFragment fragment = (DetailRequestFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frDetailsRequest);
        if (fragment != null)
            fragment.setCollection(collection);
    } // onResume

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