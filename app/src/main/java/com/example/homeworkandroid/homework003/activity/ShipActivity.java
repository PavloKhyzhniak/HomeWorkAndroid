package com.example.homeworkandroid.homework003.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework002.models.Ship;
import com.example.homeworkandroid.homework002.models.ShipType;
import com.example.homeworkandroid.homework003.MainActivityHW003;
import com.example.homeworkandroid.homework003.adapters.ShipAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class ShipActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Ship ship;

    private ShipType currentShipType;

    private Button btnReturnToHomeWork003,btnReturnToMain;
    private Button btnSummary;
    private TextView txvSummary;
    private Button btnShipActivityProcess, btnShipActivityBack;
    private EditText edtShipName, edtShipCarryingCapacity, edtShipDestination,
            edtShipCargoType, edtCargoWeight, edtCargoPrice;
    private ImageView imageViewShip;
    private LinearLayout llShip;

    private SwitchCompat swtIndicationSpecialPier, swtIndicationAnchoragePlace, swtIndicationRefueling;

    private Spinner spinner;
    private String[] paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework003_activity_ship);

        // получить параметр из вызывающей активности
        Intent intent = getIntent();
        ship = intent.getParcelableExtra(Ship.class.getCanonicalName());

        findViews();

        setListeners();

        setDefault();

        showShip(ship);
        currentShipType = ship.getType();

        fillSpinner(spinner, currentShipType);
    }


    private void showShip(Ship ship) {
        llShip.setVisibility(View.VISIBLE);

        edtShipName.setText(ship.getName());
        edtShipCarryingCapacity.setText(String.format(Locale.ROOT, "%3d", ship.getCarryingCapacity()));
        edtShipDestination.setText(ship.getDestination());
        edtShipCargoType.setText(ship.getCargoType());
        edtCargoWeight.setText(String.format(Locale.ROOT, "%3d", ship.getCargoWeight()));
        edtCargoPrice.setText(String.format(Locale.ROOT, "%3d", ship.getCargoPrice()));

        swtIndicationSpecialPier.setChecked(ship.isIndicationSpecialPier());
        swtIndicationAnchoragePlace.setChecked(ship.isIndicationAnchoragePlace());
        swtIndicationRefueling.setChecked(ship.isIndicationRefueling());

        ShowImg(imageViewShip, ship.getType().getBase());
    }

    private void ShowImg(ImageView imageView, String fileName) {
        // чтение файла изображения из assets и вывод его в ImageView
        // получить файл с изображением и поместить его в ImageView
        try (InputStream inputStream = getApplicationContext().getResources().getAssets().open(fileName)) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);

            // программное управление режимом масштвьирования
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } catch (IOException e) {
            Snackbar snackbar = Snackbar.make(imageView, "Ошибка чтения файла изображения",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", v -> {
            });
            snackbar.show();
        } // try-catсh
    }

    private void setDefault() {
        // установить incWeight и соответсвующую радиокнопку
        incWeight = 20;
        ((RadioButton) findViewById(R.id.rbtWeightInc20)).setChecked(true);
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        edtShipName = findViewById(R.id.edtShipName);
        edtShipCarryingCapacity = findViewById(R.id.edtShipCarryingCapacity);
        edtShipDestination = findViewById(R.id.edtShipDestination);
        edtShipCargoType = findViewById(R.id.edtShipCargoType);
        edtCargoWeight = findViewById(R.id.edtCargoWeight);
        edtCargoPrice = findViewById(R.id.edtCargoPrice);

        swtIndicationSpecialPier = findViewById(R.id.swtIndicationSpecialPier);
        swtIndicationAnchoragePlace = findViewById(R.id.swtIndicationAnchoragePlace);
        swtIndicationRefueling = findViewById(R.id.swtIndicationRefueling);

        spinner = findViewById(R.id.spinnerType);

        imageViewShip = findViewById(R.id.imageViewShip);

        btnReturnToHomeWork003 = findViewById(R.id.btnReturnToHomeWork003);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnShipActivityProcess = findViewById(R.id.btnShipActivityProcess);
        btnShipActivityBack = findViewById(R.id.btnShipActivityBack);

        btnSummary = findViewById(R.id.btnSummary);
        txvSummary = findViewById(R.id.txvSummary);

        llShip = findViewById(R.id.llShip);
    }

    private void setListeners() {
        btnReturnToHomeWork003.setOnClickListener(v -> returnToHomeWork003());
        btnReturnToMain.setOnClickListener(v -> returnToMain());

        btnShipActivityProcess.setOnClickListener(v -> processAnimalActivity());
        btnShipActivityBack.setOnClickListener(v -> backAnimalActivity());

        edtCargoWeight.setOnFocusChangeListener((v,i)-> Summary());
        edtCargoPrice.setOnFocusChangeListener((v,i)-> Summary());
        btnSummary.setOnClickListener(v -> Summary());
    }

    private void Summary() {
        int summary =
                Integer.parseInt(edtCargoWeight.getText().toString().trim())*Integer.parseInt(edtCargoPrice.getText().toString().trim());
        txvSummary.setText(String.format(Locale.ROOT, "%3d", summary));
    }

    private int incWeight;
    // обработчик клика по радиокнопке из группы приращения возраста
    @SuppressLint("NonConstantResourceId")
    public void onClickWeightIncHandler(View view) {
        RadioButton rb = (RadioButton) view;

        // так можно выяснить отмечен или не отмечен RadioButton
        // boolean checked = rb.isChecked();

        switch (rb.getId()) {
            case R.id.rbtWeightInc20:
                incWeight = +20;
                break;
            case R.id.rbtWeightDec20:
                incWeight = -20;
                break;
        } // switch
    } // onClickAgeIncHandler

    // обработчик клика по switch
    @SuppressLint("NonConstantResourceId")
    public void onClickUseHandler(View view) {
        SwitchCompat swtUse = (SwitchCompat)view;

        switch (swtUse.getId()) {
            case R.id.swtIndicationAnchoragePlace:
                ship.setIndicationAnchoragePlace(swtIndicationAnchoragePlace.isChecked());
                break;
            case R.id.swtIndicationSpecialPier:
                ship.setIndicationSpecialPier(swtIndicationSpecialPier.isChecked());
                break;
            case R.id.swtIndicationRefueling:
                ship.setIndicationRefueling(swtIndicationRefueling.isChecked());
                break;
        } // switch
    } // onClickUseHandler

    private void fillSpinner(Spinner spinner, ShipType type) {
        ShipType[] arr = ShipType.values();
        int cnt = arr.length;

        paths = new String[cnt];
        int selected = 0;
        for (int i = 0; i < cnt; i++) {
            paths[i] = arr[i].getName();

            if (arr[i].equals(type))
                selected = i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(com.example.homeworkandroid.homework003.activity.ShipActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(selected);
    }

    private void processAnimalActivity() {
        ship.setName(edtShipName.getText().toString());
        ship.setCarryingCapacity(Integer.parseInt(edtShipCarryingCapacity.getText().toString().trim()));
        ship.setDestination(edtShipDestination.getText().toString());
        ship.setType(currentShipType);
        ship.setCargoType(edtShipCargoType.getText().toString());
        ship.setCargoWeight(Integer.parseInt(edtCargoWeight.getText().toString().trim()));
        ship.setCargoPrice(Integer.parseInt(edtCargoPrice.getText().toString().trim()));
        ship.setIndicationAnchoragePlace(swtIndicationAnchoragePlace.isChecked());
        ship.setIndicationSpecialPier(swtIndicationSpecialPier.isChecked());
        ship.setIndicationRefueling(swtIndicationRefueling.isChecked());

        ship.setCargoWeight(ship.getCargoWeight() + incWeight);

        showShip(ship);
    }

    private void backAnimalActivity() {
        Intent intent = new Intent();
        intent.putExtra(Ship.class.getCanonicalName(), ship);

        setResult(ShipAdapter.RESULT_OK, intent);
        finish();
    }
    private void returnToHomeWork003() {
        Intent myIntent = new Intent(this, MainActivityHW003.class);
        startActivity(myIntent);
    }
    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long checkedId) {
        ShipType[] arr = ShipType.values();

        currentShipType = arr[(int) checkedId];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}