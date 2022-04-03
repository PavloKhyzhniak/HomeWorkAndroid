package com.example.homeworkandroid.homework002;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.util.Locale;

public class Exercises002 extends AppCompatActivity {

    // коды активностей для вызова
    public static final int SHIP_ACTIVITY = 1010;

    // коды завершения работы активности
    public static final int RESULT_OK = 0, RESUL_ERR = 1;

    private Ship ship;

    private Button btnGoToExercises001, btnReturnToHomeWork002, btnReturnToMain;
    private Button btnShipProcess, btnCreateShip;
    private TextView txvShipType, txvShipName, txvShipCarryingCapacity, txvShipDestination,
            txvShipCargoType, txvCargoWeight, txvCargoPrice,
            txvIndicationSpecialPier, txvIndicationAnchoragePlace, txvIndicationRefueling;
    private ImageView imageViewShip;
    private LinearLayout llShip;

    private RadioGroup rgrChooseShip;
    private RadioButton rbtShipBalker, rbtShipConteiner, rbtShipParom, rbtShipRolker, rbtShipSuhogruz, rbtShipTanker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises2);

        ship = new Ship();
        findViews();

        setListeners();

        createShip();
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        txvShipType = findViewById(R.id.txvShipType);
        txvShipName = findViewById(R.id.txvShipName);
        txvShipCarryingCapacity = findViewById(R.id.txvShipCarryingCapacity);
        txvShipDestination = findViewById(R.id.txvShipDestination);
        txvShipCargoType = findViewById(R.id.txvShipCargoType);
        txvCargoWeight = findViewById(R.id.txvCargoWeight);
        txvCargoPrice = findViewById(R.id.txvCargoPrice);
        txvIndicationSpecialPier = findViewById(R.id.txvIndicationSpecialPier);
        txvIndicationAnchoragePlace = findViewById(R.id.txvIndicationAnchoragePlace);
        txvIndicationRefueling = findViewById(R.id.txvIndicationRefueling);

        imageViewShip = findViewById(R.id.imageViewShip);

        btnGoToExercises001 = findViewById(R.id.btnGoToExercises001);
        btnReturnToHomeWork002 = findViewById(R.id.btnReturnToHomeWork002);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnShipProcess = findViewById(R.id.btnShipProcess);
        btnCreateShip = findViewById(R.id.btnCreateShip);

        llShip = findViewById(R.id.llShip);

        rgrChooseShip = findViewById(R.id.rgrChooseShip);
        rbtShipBalker = findViewById(R.id.rbtShipBalker);
        rbtShipConteiner = findViewById(R.id.rbtShipConteiner);
        rbtShipParom = findViewById(R.id.rbtShipParom);
        rbtShipRolker = findViewById(R.id.rbtShipRolker);
        rbtShipSuhogruz = findViewById(R.id.rbtShipSuhogruz);
        rbtShipTanker = findViewById(R.id.rbtShipTanker);
    }

    private void setListeners() {
        btnGoToExercises001.setOnClickListener(v -> gotoExercises001Click());
        btnReturnToHomeWork002.setOnClickListener(v -> returnToHomeWork002());
        btnReturnToMain.setOnClickListener(v -> returnToMain());

        btnShipProcess.setOnClickListener(v -> startShipActivity());
        btnCreateShip.setOnClickListener(v -> createShip());
    }

    // обработчик клика по switch
    public void onClickChooseShipHandler(View view) {
        RadioButton rbtChoose = (RadioButton)view;

        // так можно выяснить отмечен или не отмечен RadioButton
        // boolean checked = rb.isChecked();

        switch (rbtChoose.getId()) {
            case R.id.rbtShipBalker:
                ship = new Ship("Первопроходец",25,"Сингапур",ShipType.bulkCarrier,"мусор",2000,1600,false,false,true);
                break;
            case R.id.rbtShipConteiner:
                ship = new Ship("Первопроходец",25,"Сингапур",ShipType.containerShip,"мусор",2000,1600,true,false,true);
                break;
            case R.id.rbtShipParom:
                ship = new Ship("Первопроходец",25,"Сингапур",ShipType.ferry,"мусор",2000,1600,false,true,true);
                break;
            case R.id.rbtShipRolker:
                ship = new Ship("Первопроходец",25,"Сингапур",ShipType.rolker,"мусор",2000,1600,false,true,false);
                break;
            case R.id.rbtShipSuhogruz:
                ship = new Ship("Первопроходец",25,"Сингапур",ShipType.dryCargoShip,"мусор",2000,1600,true,false,false);
                break;
            case R.id.rbtShipTanker:
                ship = new Ship("Первопроходец",25,"Сингапур",ShipType.tanker,"мусор",2000,1600,true,true,false);
                break;
        } // switch

        rgrChooseShip.setVisibility(View.GONE);
    showShip(ship);
    } // onClickUseHandler

    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    private void returnToHomeWork002() {
        Intent myIntent = new Intent(this, MainActivityHW002.class);
        startActivity(myIntent);
    }

    private void gotoExercises001Click() {
        Intent myIntent = new Intent(this, Exercises001.class);
        startActivity(myIntent);
    }

    private void createShip() {
        rgrChooseShip.setVisibility(View.VISIBLE);
        llShip.setVisibility(View.GONE);
    } // createShip

    // обработчик клика по кнопке вызова активности для Animal
    private void startShipActivity() {
        if(ship!=null) {
            Intent intent = new Intent(this, ShipActivity.class);
            intent.putExtra(Ship.class.getCanonicalName(), ship);
            startActivityForResult(intent, SHIP_ACTIVITY);
        }
    } // startUserActivity

    private void showShip(Ship ship) {
        rgrChooseShip.setVisibility(View.GONE);
        llShip.setVisibility(View.VISIBLE);

        txvShipType.setText(ship.getType().getName());
        txvShipName.setText(ship.getName());
        txvShipCarryingCapacity.setText(String.format(Locale.ROOT, "%3d", ship.getCarryingCapacity()));
        txvShipDestination.setText(ship.getDestination());
        txvShipCargoType.setText(ship.getCargoType());
        txvCargoWeight.setText(String.format(Locale.ROOT, "%3d", ship.getCargoWeight()));
        txvCargoPrice.setText(String.format(Locale.ROOT, "%3d", ship.getCargoPrice()));


        txvIndicationSpecialPier.setVisibility(ship.isIndicationSpecialPier() ? View.VISIBLE : View.GONE);
        txvIndicationAnchoragePlace.setVisibility(ship.isIndicationAnchoragePlace() ? View.VISIBLE : View.GONE);
        txvIndicationRefueling.setVisibility(ship.isIndicationRefueling() ? View.VISIBLE : View.GONE);

        ShowImg(imageViewShip, ship.getType().getBase());
    }

    private void ShowImg(ImageView imageView, String fileName) {
        // чтение файла изображения из assets и вывод его в ImageView
        // получить файл с изображением и поместить его в ImageView
        try (InputStream inputStream = getApplicationContext().getResources().getAssets().open(fileName)) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);

            // программное управление режимом масштвьирования
            //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } catch (IOException e) {
            Snackbar snackbar = Snackbar.make(imageView, "Ошибка чтения файла изображения",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", v -> {
            });
            snackbar.show();
        } // try-catсh
    }

    //region Сохранение и восстановление контекста при повороте устройства
    // т.е. Stop -> Start
    @Override // сохранение состояния при повороте устройства
    public void onSaveInstanceState(Bundle outState) {
        // Bundle - коллекция пар ключ-значение
        // необходимо сохранить объекты данных
        outState.putParcelable(Ship.class.getCanonicalName(), ship);

        super.onSaveInstanceState(outState);
    } // onSaveInstanceState


    @Override // Восстановление значений, сохраненных при повороте устройства
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // получение ранее сохраненных параметров
        ship = savedInstanceState.getParcelable(Ship.class.getCanonicalName());
        showShip(ship);

    } // onRestoreInstanceState
//endregion

    // обработчик события получения данных из другой активности
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(this,
                    String.format("Ошибка %d в активности %d", resultCode, requestCode),
                    Toast.LENGTH_LONG)
                    .show();
            return;
        } // if

        // при успешной работе активностей - принять данные, вывести в TextView
        switch (requestCode) {
            case SHIP_ACTIVITY:
                _ACTIVITY:
                ship = data.getParcelableExtra(Ship.class.getCanonicalName());
                showShip(ship);
                break;
        } // switch
    }
}