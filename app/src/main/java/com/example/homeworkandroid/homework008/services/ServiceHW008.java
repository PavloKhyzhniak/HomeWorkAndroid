package com.example.homeworkandroid.homework008.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework008.models.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// синхронный вызов методов сервиса из активности
public class ServiceHW008 extends Service {

    // назначенные самостоятельно коды запросов (ид задач)
    public final static int TASK1_CODE = 1;
    public final static int TASK2_CODE = 2;
    public final static int TASK3_CODE = 3;

    // назначенные самостоятельно статусы для запросов
    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    // имена параметров для передачи в сервисы
    public final static String PARAM_TIME    = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT  = "result";
    public final static String PARAM_MESSAGE = "message";
    public final static String PARAM_COLLECTION = "collection";

    final String LOG_TAG = "ServiceHW008";

    // объект для привязки сервиса к активности - дает
    // возможность синхронного вызова методов сервиса
    // из активности
    MyBinder binder = new MyBinder();

    Timer timer;             // таймер для периодического запука сервиса
    TimerTask tTask;         // задача, запускаяемая по таймеру
    long interval = 3_000;   // начальный интервал запуска задач

    ExecutorService es;

    // ссылка на менеджер уведомлений
    NotificationManager nm;
    final String CHANNEL_ID = ServiceHW008.class.getCanonicalName();

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "Service onCreate");

        // таймер и планировщик обеспечивают периодический запуск полезной
        // работы сервиса
        timer = new Timer();

        // пул потоков для работы задач (сервиса)
        es = Executors.newFixedThreadPool(1);

        // получить ссылку на менеджер уведомлений
        // https://developer.android.com/training/notify-user/build-notification#java
        nm = createNotificationChannel();

    } // onCreate

    Intent intent; int flags; int startId;
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "Service onStartCommand");

        this.intent = intent;
        this.flags = flags;
        this.startId = startId;

        schedule();

        return super.onStartCommand(intent, flags, startId);
    } // onStartCommand


    // планировщик периодического запуска задачи - полезной работы сервиса
    void schedule() {
        if (tTask != null) tTask.cancel();

        if (interval > 0) {
            // создание задачи таймера - то, что будет запускаться
            tTask = new TimerTask() {
                public void run() {
                    Log.d(LOG_TAG, "run - полезное действие задачи TimerTask");

                    if(intent==null)
                        return;

                    int time = intent.getIntExtra(ServiceHW008.PARAM_TIME, 1);
                    PendingIntent pi = intent.getParcelableExtra(ServiceHW008.PARAM_PINTENT);

                    // создать и запустить полезную часть сервиса (в пуле потоков)
                    MyRun mr = new MyRun(time, startId, pi);
                    es.execute(mr);

                    sendNotify(startId);  // вызываем метод для запуска уведомления
                }
            };

            // запуск задачи с задержкой 1000 мс и периодом interval мс
            timer.schedule(tTask, 1_000, interval);
        } // if
    } // schedule
    private static int cnt = 0;

    class MyRun implements Runnable {

        private int time;           // данные для обработки - время
        private int startId;        // идентификатор
        private PendingIntent pi;   // для связи с активностью

        public MyRun(int time, int startId, PendingIntent pi) {
            this.time = time;
            this.startId = startId;
            this.pi = pi;
            Log.d(LOG_TAG, String.format("MyRun#%d: создан", startId));
        } // MyRun

        // реализуем полезную работу сервиса
        public void run() {
            Log.d(LOG_TAG, String.format("MyRun#%d: запущен, time = %d", startId, time));
            try {
                // сообщаем о старте задачи - отправляем интент pi в активность
                pi.send(ServiceHW008.STATUS_START);

                // полезная работа сервиса :)
                int result = time * 100;

                // сообщаем об окончании задачи и взвращаем результат
                // а) сформировать возвращаемый пакет данных в интенте
                Intent intent = new Intent()
                        .putExtra(ServiceHW008.PARAM_RESULT, result)
                        .putExtra(ServiceHW008.PARAM_MESSAGE, new Message(String.format("New Message %d",cnt*cnt),cnt++,cnt%3==0?true:false));
                // б) отправить интент с данным в активность - при помощи PendingIntent
                pi.send(ServiceHW008.this, ServiceHW008.STATUS_FINISH, intent);

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            } // try-catch
            stop();
        } // run

        // завершение сервиса оформим в отдельный метод
        void stop() {
            boolean result = stopSelfResult(startId);
            Log.d(LOG_TAG, String.format("MyRun#%1$d завершен, stopSelfResult(%1$d) = %2$b", startId, result));
        } // stop
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tTask != null)
            tTask.cancel();

        Log.d(LOG_TAG, "Service onDestroy");
    } // onDestroy

    public IBinder onBind(Intent arg0) {
        Log.d(LOG_TAG, "Service onBind");
        return binder;
    } // onBind

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "Service onRebind");
    } // onRebind

    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "Service onUnbind");
        return super.onUnbind(intent);
    } // onUnbind

    // создание канала для уведомлений
    private NotificationManager createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            return notificationManager;
        } else
            // для версий меньше 8.0+
            return (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    }

    // запуск/отправка уведомления
    void sendNotify(int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.submarine)
                .setContentTitle("Заголовок")
                .setContentText(String.format(Locale.UK, "Текст уведомления %d", id))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        nm.notify(startId, builder.build());

        //auto cancel notification
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                nm.cancel(startId);
            }
        }, 10_000);

        Log.d(LOG_TAG, "уведомление отправлено");
    } // sendNotify

    public void sendNotify(List<Message> collection) {
        int cnt_text = 0;
        int cnt_attached = 0;
        for (Message item :collection)
        {
            cnt_text+=item.getText().length();
            if(item.getFlagAttached())
                cnt_attached++;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.submarine)
                .setContentTitle("Статистика")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(String.format(Locale.UK, "Суммарная длинна текстов %d, количество сообщений с вложениями %d", cnt_text,cnt_attached)));

        nm.notify(startId+1, builder.build());

        Log.d(LOG_TAG, "уведомление отправлено");
    } // sendNotify

    // внутренний класс - нужен для получения ссылки на сервис
    // в активности - для прямого обращения к сервису при
    // использовании привязки
    public class MyBinder extends Binder {
        // только ради этого метода и был создан MyBinder
        public ServiceHW008 getService() {
            return ServiceHW008.this;
        } // getService
    } // class MyBinder
} // class MyService
