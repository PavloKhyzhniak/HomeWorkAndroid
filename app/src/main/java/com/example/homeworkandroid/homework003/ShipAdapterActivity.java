package com.example.homeworkandroid.homework003;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework002.Ship;
import com.example.homeworkandroid.homework002.ShipType;
import com.example.homeworkandroid.homework003.adapters.ShipAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipAdapterActivity extends AppCompatActivity {

    private List<Ship> ships;

    private ListView lsvShipAdapter;
    private ShipAdapter shipActionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_adapter);

        initializer();

        // список
        // 1. Получение ссылки на ListView
        lsvShipAdapter = findViewById(R.id.lsvShipAdapter);

        // 2. Создание адаптера
        // адаптер попроще
        shipActionAdapter = new ShipAdapter(
                this,
                R.layout.activity_ship_adapter_item,
                ships);

        // 3. Назаначение адаптера
        lsvShipAdapter.setAdapter(shipActionAdapter);

    }

    void initializer() {
        ships =  new ArrayList<>(Arrays.asList(
        new Ship("Первопроходец",25,"Сингапур", ShipType.bulkCarrier,"мусор",2000,1600,false,false,true),
        new Ship("Живорожденный",250,"Владивосток",ShipType.containerShip,"мусор",2000,1600,true,false,true),
        new Ship("Скалистый змей",205,"Урал",ShipType.ferry,"мусор",2000,1600,false,true,true),
        new Ship("Сундук сокровищ",15,"Болгария",ShipType.rolker,"мусор",2000,1600,false,true,false),
        new Ship("Морской дьявол",25,"Киль",ShipType.dryCargoShip,"мусор",2000,1600,true,false,false),
        new Ship("Ведьма",56,"Гамбург",ShipType.tanker,"мусор",2000,1600,true,true,false)
                 ));
    } // initializer


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
    // endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shipActionAdapter.onActivityResult(requestCode, resultCode, data);
    }
}