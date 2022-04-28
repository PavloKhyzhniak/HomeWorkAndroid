package com.example.homeworkandroid.homework007.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.adapters.AviasalesAdapter;
import com.example.homeworkandroid.homework007.fragments.EditAviasalesDialogFragment;
import com.example.homeworkandroid.homework007.fragments.InfoAviasalesDialogFragment;
import com.example.homeworkandroid.homework007.models.Aviasales;
import com.example.homeworkandroid.homework007.models.CollectionAviasales;
import com.example.homeworkandroid.homework007.models.JsonHelper;

import java.text.ParseException;
import java.util.List;

public class Exercises001 extends AppCompatActivity implements InfoAviasalesDialogFragment.OnFragmentSendDataListener ,
        EditAviasalesDialogFragment.OnFragmentSendDataListener {

    private Button btnReturnToMain,
    btnSave, btnLoad;

    // данные для сохранения/загрузки
    private CollectionAviasales collectionAviasales;

    // элемент отображения списка
    ListView lsvItemsList;
    ArrayAdapter<Aviasales> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework007_activity_exercises001);

        // создали коллекцию товаров для обработки, при создании
        // выполняется заполнение коллекции начальными значениями
        collectionAviasales = new CollectionAviasales();

        findViews();

        setListeners();

        refreshAdapter();
    }


    private void refreshAdapter()
    {

//                itemsAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1,
//                collectionAviasales.getItemsList());
        itemsAdapter = new AviasalesAdapter(this, R.layout.homework007_aviasales_item,
                collectionAviasales.getItemsList());

        // устанавливаем для списка адаптер
        lsvItemsList.setAdapter(itemsAdapter);
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);

        lsvItemsList = findViewById(R.id.lsvItemsList);
    }

    private void setListeners() {
        btnReturnToMain.setOnClickListener(this::OnClickListener);

        btnSave.setOnClickListener(v->save());
        btnLoad.setOnClickListener(v->load());
    }

    // сохранить коллекцию в JSON-файл
    private void save() {
        boolean result = JsonHelper.exportToJSON(this, collectionAviasales.getItemsList());
        String str = result?"Данные сохранены":"Не удалось сохранить данные";
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    } // saveStore

    // загрузить коллекцию из JSON-файла
    private void load() {
        // чтение коллекции из JSON-файла
        List<Aviasales> list = JsonHelper.importFromJSON(this);
        String str = "";

        if(list != null){
            collectionAviasales.setItemsList(list);

            refreshAdapter();

            str = "Данные восстановлены";
        } else {
            str = "Не удалось восстановить данные";
        } //if

        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    } // loadStore

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
    public void onSendData(int position) throws ParseException {
        collectionAviasales.getItemsList().remove(position);
    }

    @Override
    public void onSendData(int position, Aviasales item) throws ParseException {
        if(position == -1) {
            //create new item
            collectionAviasales.getItemsList().add(item);
        }
        else
        {
            //refresh old item
            collectionAviasales.getItemsList().set(position,item);
        }
    }


    // endregion
}