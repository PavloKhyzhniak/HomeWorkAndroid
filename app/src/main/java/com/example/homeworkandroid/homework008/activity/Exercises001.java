package com.example.homeworkandroid.homework008.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework008.adapters.MessagesAdapter;
import com.example.homeworkandroid.homework008.models.CollectionMessage;
import com.example.homeworkandroid.homework008.models.Message;
import com.example.homeworkandroid.homework008.services.ServiceHW008;

import java.util.ArrayList;

public class Exercises001 extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private Button btnReturnToMain,
            btnRun, btnStop;

    // данные для сохранения/загрузки
    private CollectionMessage collection;

    // элемент отображения списка
    ListView lsvItemsList;
    ArrayAdapter<Message> itemsAdapter;

    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    ServiceHW008 service;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework008_activity_exercises001);

        // создали коллекцию товаров для обработки, при создании
        // выполняется заполнение коллекции начальными значениями
        collection = new CollectionMessage();

        PendingIntent pi;
        // Создаем PendingIntent для Task1
        // параметры - ид, интент (до версии 4 можно было передавать null), флаги
        pi = createPendingResult(ServiceHW008.TASK1_CODE, new Intent(), 0);

        intent = new Intent(this, ServiceHW008.class)
                .putExtra(ServiceHW008.PARAM_TIME, 7)
                .putExtra(ServiceHW008.PARAM_PINTENT, pi);

        /*
         * Объект ServiceConnection позволит нам определить, когда мы подключились к сервису и
         * когда связь с сервисом потеряна (если сервис был убит системой при нехватке памяти).
         * При подключении к сервису сработает метод onServiceConnected. На вход он получает
         * имя компонента-сервиса и объект Binder для взаимодействия с сервисом.
         * В этом приложении мы Binder пока не пользуемся.
         * При потере связи сработает метод onServiceDisconnected.
         * Переменную bound используем для того, чтобы знать – подключены мы в данный момент к
         * сервису (bound установлен в true) или нет (bound установлен в false).
         *
         * */

        // В методе onServiceConnected мы берем binder, преобразуем его к MyService.MyBinder,
        // вызываем метод getService и получаем наш сервис MyService.
        // Теперь мы можем выполнять методы сервиса.
        sConn = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "HomeWork 008 Exercises001 onServiceConnected");
                service = ((ServiceHW008.MyBinder) binder).getService();
                bound = true;
            } // onServiceConnected

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "HomeWork 008 Exercises001 onServiceDisconnected");
                bound = false;
            } // onServiceDisconnected
        };



        findViews();

        setListeners();

        refreshAdapter();
    } // onCreate

    private void refreshAdapter() {
//        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                collection.getItemsList());
        itemsAdapter = new MessagesAdapter(this, R.layout.homework008_message_item,
                collection.getItemsList());

        // устанавливаем для списка адаптер
        lsvItemsList.setAdapter(itemsAdapter);
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnRun = findViewById(R.id.btnRun);
        btnStop = findViewById(R.id.btnStop);

        lsvItemsList = findViewById(R.id.lsvItemsList);
    }

    private void setListeners() {
        btnReturnToMain.setOnClickListener(this::OnClickListener);

        btnRun.setOnClickListener(v ->
        {
            Log.d(LOG_TAG, "HomeWork 008 Exercises001 onClickStart");
            startService(this.intent);
        });
        btnStop.setOnClickListener(v ->
        {
            Log.d(LOG_TAG, "HomeWork 008 Exercises001 onClickStop");
            stopService(this.intent);
            service.onDestroy();
        });
    }

    // Соединяемся с сервисом, используя метод bindService. На вход передаем Intent,
    // ServiceConnection и флаг BIND_AUTO_CREATE (если сервис, к которому мы пытаемся
    // подключиться, не работает, то он будет запущен).
    public void onClickBind(View v) {
        Log.d(LOG_TAG, "HomeWork 008 Exercises001 onClickBind");

        // intent - интент для запуска сервиса
        // sConn  - ServiceConnection для определения подключения/отключения к сервису/от сервиса
        // BIND_AUTO_CREATE - запустить сервис, если он еще не запущен
        bindService(this.intent, sConn, BIND_AUTO_CREATE);
        // bindService(intent, sConn, 0);
    } // onClickBind

    // Отсоединяемся методом unbindService, на вход передавая ему ServiceConnection.
    // И в bound пишем false, т.к. мы сами разорвали соединение.
    public void onClickUnBind(View v) {
        if (!bound) return;
        Log.d(LOG_TAG, "HomeWork 008 Exercises001 onClickUnBind");

        unbindService(sConn);
        bound = false;
    } // onClickUnBind

    @Override // получает результат из сервисов
    // параметр data - это интент, полученный из сервиса
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, String.format("requestCode = %d, resultCode = %d", requestCode, resultCode));

        switch (resultCode) {
            // Ловим сообщения о старте задач
            case ServiceHW008.STATUS_START:
                switch (requestCode) {
                    case ServiceHW008.TASK1_CODE:
                        break;
                    case ServiceHW008.TASK2_CODE:
                        break;
                    case ServiceHW008.TASK3_CODE:
                        break;
                } // switch
                break;

            // Ловим сообщения об окончании задач
            case ServiceHW008.STATUS_FINISH:
                // т.к. это финиш сервиса, то получаем данные из этого сервиса,
                // извлекая данные из полученного интента data
                Message result = data.getParcelableExtra(ServiceHW008.PARAM_MESSAGE);
                Log.d(LOG_TAG, String.format("result = %s %d ", result.getText(), result.getSenderID()));

                switch (requestCode) {
                    case ServiceHW008.TASK1_CODE:
                        AddMessage(result);
                        break;
                    case ServiceHW008.TASK2_CODE:
                        break;
                    case ServiceHW008.TASK3_CODE:
                        break;
                } // switch
                break;
        } // switch
    } // onActivityResult

    public void AddMessage(Message message)
    {
        collection.getItemsList().add(message);
        itemsAdapter.notifyDataSetChanged();

        service.sendNotify(collection.getItemsList());
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
    protected void onStart() {
        super.onStart();
        // intent - интент для запуска сервиса
        // sConn  - ServiceConnection для определения подключения/отключения к сервису/от сервиса
        // BIND_AUTO_CREATE - запустить сервис, если он еще не запущен
        bindService(intent, sConn, BIND_AUTO_CREATE);
        // bindService(intent, sConn, 0);
    } // onStart

    @Override
    protected void onStop() {
        super.onStop();
        if (!bound)
            return;

        unbindService(sConn);
        bound = false;
    } // onStop


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (bound)
            unbindService(sConn);
        stopService(intent);
    } // onDestroy
}