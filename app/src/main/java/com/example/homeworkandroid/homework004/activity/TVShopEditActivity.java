package com.example.homeworkandroid.homework004.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework002.MainActivityHW002;
import com.example.homeworkandroid.homework002.Ship;
import com.example.homeworkandroid.homework002.ShipType;
import com.example.homeworkandroid.homework003.adapters.ShipAdapter;
import com.example.homeworkandroid.homework004.MainActivityHW004;
import com.example.homeworkandroid.homework004.adapters.TVShopAdapter;
import com.example.homeworkandroid.homework004.models.TVShop;
import com.example.homeworkandroid.homework004.models.TVShopManufacturer;
import com.example.homeworkandroid.homework004.models.TVShopType;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class TVShopEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TVShop tvShop;

    private TVShopManufacturer currentManufacturer;

    // ссылки на элементы интерфейса
    private RelativeLayout layout_item;
    private ImageView imgManufacturer,imgType;
    private EditText edtTypeName, edtSize, edtHorizontal, edtVertical, edtPrice;

    private Button btnReturnToHomeWork004, btnReturnToMain;
    private Button btnProcess;
    private Button btnBack;

    private Spinner spinner;
    private String[] paths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshop_edit);

        // получить параметр из вызывающей активности
        Intent intent = getIntent();
        tvShop = intent.getParcelableExtra(TVShop.class.getCanonicalName());

        findViews();

        setListeners();

        //setDefault();

        show(tvShop);
        currentManufacturer = tvShop.getManufacturer();

        fillSpinner(spinner, currentManufacturer);
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса

        edtTypeName = findViewById(R.id.edtTypeName);
        edtSize = findViewById(R.id.edtSize);
        edtHorizontal = findViewById(R.id.edtHorizontal);
        edtVertical = findViewById(R.id.edtVertical);
        edtPrice = findViewById(R.id.edtPrice);

        spinner = findViewById(R.id.spinnerManufacturer);

        imgType = findViewById(R.id.imgType);

        btnReturnToHomeWork004 = findViewById(R.id.btnReturnToHomeWork004);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnProcess = findViewById(R.id.btnProcess);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setListeners() {
        btnReturnToHomeWork004.setOnClickListener(v -> returnToHomeWork004());
        btnReturnToMain.setOnClickListener(v -> returnToMain());

        btnProcess.setOnClickListener(v -> processActivity());
        btnBack.setOnClickListener(v -> backActivity());
    }

    private void show(TVShop tvShop) {
        // связать разметку и ссылки на элементы отображения
        ShowImg(imgType,tvShop.getType().getBase());

        edtTypeName.setText(tvShop.getName().toString());
        edtSize.setText(String.format(Locale.UK, "%.3f", tvShop.getSize()));
        edtHorizontal.setText(String.format(Locale.UK, "%3d", tvShop.getHorizontal()));
        edtVertical.setText(String.format(Locale.UK, "%3d", tvShop.getVertical()));
        edtPrice.setText(String.format(Locale.UK, "%3d", tvShop.getPrice()));
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

    public class SimpleImageArrayAdapter extends ArrayAdapter<String> {
        private String[] images;

        public SimpleImageArrayAdapter(Context context, String[] images) {
            super(context, android.R.layout.simple_spinner_item, images);
            this.images = images;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        private View getImageForPosition(int position) {
            ImageView imageView = new ImageView(getContext());
            ShowImg(imageView, images[position]);
//            imageView.setBackgroundResource(images[position]);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return imageView;
        }
    }
    private void fillSpinner(Spinner spinner, TVShopManufacturer type) {
        TVShopManufacturer[] arr = TVShopManufacturer.values();
        int cnt = arr.length;

        paths = new String[cnt];
        int selected = 0;
        for (int i = 0; i < cnt; i++) {
            paths[i] = arr[i].getBase();

            if (arr[i].equals(type))
                selected = i;
        }

        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(this, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(selected);
    }

    private void processActivity() {
        tvShop.setManufacturer(currentManufacturer);
        TVShopType type = tvShop.getType();
        type.setSize(Double.parseDouble(edtSize.getText().toString().trim()));
        type.setHorizontal(Integer.parseInt(edtHorizontal.getText().toString().trim()));
        type.setVertical(Integer.parseInt(edtVertical.getText().toString().trim()));

        tvShop.setPrice(Integer.parseInt(edtPrice.getText().toString().trim()));

        show(tvShop);
    }

    private void backActivity() {
        Intent intent = new Intent();
        intent.putExtra(TVShop.class.getCanonicalName(), tvShop);

        setResult(TVShopAdapter.RESULT_OK, intent);
        finish();
    }

    private void returnToHomeWork004() {
        Intent myIntent = new Intent(this, MainActivityHW004.class);
        startActivity(myIntent);
    }

    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long checkedId) {
        TVShopManufacturer[] arr = TVShopManufacturer.values();

        currentManufacturer = arr[(int) checkedId];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}