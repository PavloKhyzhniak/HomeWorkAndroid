package com.example.homeworkandroid.homework002;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class Exercises001 extends AppCompatActivity {

    // коды активностей для вызова
    public static final int ANIMAL_ACTIVITY = 1010;

    // коды завершения работы активности
    public static final int RESULT_OK = 0, RESUL_ERR = 1;

    private Animal animal;

    private Button btnGoToExercises002, btnReturnToHomeWork002,btnReturnToMain;
    private Button btnAnimalProcess, btnCreateAnimal;
    private TextView txvAnimalBreed, txvAnimalName, txvAnimalAge, txvAnimalWeight, txvAnimalLastName, txvAnimalFirstName, txvAnimalType;
    private ImageView imageViewImg;
    private ImageView imageViewTypeIcon;
    private LinearLayout llAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises1);

        animal = new Animal();
        findViews();

        setListeners();
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        txvAnimalBreed = findViewById(R.id.txvAnimalBreed);
        txvAnimalName = findViewById(R.id.txvAnimalName);
        txvAnimalAge = findViewById(R.id.txvAnimalAge);
        txvAnimalWeight = findViewById(R.id.txvAnimalWeight);
        txvAnimalLastName = findViewById(R.id.txvAnimalLastName);
        txvAnimalFirstName = findViewById(R.id.txvAnimalFirstName);
        txvAnimalType = findViewById(R.id.txvAnimalType);

        imageViewImg = findViewById(R.id.imageViewImg);
        imageViewTypeIcon = findViewById(R.id.imageViewTypeIcon);

        btnGoToExercises002 = findViewById(R.id.btnGoToExercises002);
        btnReturnToHomeWork002 = findViewById(R.id.btnReturnToHomeWork002);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnAnimalProcess = findViewById(R.id.btnAnimalProcess);
        btnCreateAnimal = findViewById(R.id.btnCreateAnimal);

        llAnimal = findViewById(R.id.llAnimal);
    }

    private void setListeners() {
        btnGoToExercises002.setOnClickListener(v -> gotoExercises002Click());
        btnReturnToHomeWork002.setOnClickListener(v -> returnToHomeWork002());
        btnReturnToMain.setOnClickListener(v -> returnToMain());

        btnAnimalProcess.setOnClickListener(v -> startAnimalActivity());
        btnCreateAnimal.setOnClickListener(v -> createAnimal());
    }

    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
    private void returnToHomeWork002() {
        Intent myIntent = new Intent(this, MainActivityHW002.class);
        startActivity(myIntent);
    }
    private void gotoExercises002Click() {
        Intent myIntent = new Intent(this, Exercises002.class);
        startActivity(myIntent);
    }

    private void createAnimal() {
        animal = new Animal();
        showAnimal(animal);
    } // createAnimal

    // обработчик клика по кнопке вызова активности для Animal
    private void startAnimalActivity() {
        if(animal!=null) {
            Intent intent = new Intent(this, AnimalActivity.class);
            intent.putExtra(Animal.class.getCanonicalName(), animal);
            startActivityForResult(intent, ANIMAL_ACTIVITY);
        }
    } // startUserActivity

    //region Сохранение и восстановление контекста при повороте устройства
    // т.е. Stop -> Start
    @Override // сохранение состояния при повороте устройства
    public void onSaveInstanceState(Bundle outState) {
        // Bundle - коллекция пар ключ-значение
        // необходимо сохранить объекты данных
        outState.putParcelable(Animal.class.getCanonicalName(), animal);

        super.onSaveInstanceState(outState);
    } // onSaveInstanceState


    @Override // Восстановление значений, сохраненных при повороте устройства
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // получение ранее сохраненных параметров
        animal = savedInstanceState.getParcelable(Animal.class.getCanonicalName());
        showAnimal(animal);

    } // onRestoreInstanceState

    private void showAnimal(Animal animal) {
        llAnimal.setVisibility(View.VISIBLE);

        txvAnimalBreed.setText(animal.getBreed());
        txvAnimalName.setText(animal.getName());
        txvAnimalAge.setText(String.format(Locale.ROOT,"%3d",animal.getAge()));
        txvAnimalWeight.setText(String.format(Locale.ROOT,"%3d",animal.getWeight()));
        txvAnimalLastName.setText(animal.getLastNameOwner());
        txvAnimalFirstName.setText(animal.getFistNameOwner());
        txvAnimalType.setText(animal.getType().getName());

        if (animal.getImg() == "") {
            ShowImg(imageViewImg, animal.getType().getBase());
        } else {
            ShowGalleryImg(imageViewImg, animal.getImg());
        }
        ShowImg(imageViewTypeIcon, animal.getType().getBase());
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

    private void ShowGalleryImg(ImageView imageView, String picturePath) {
        try {
//            // Parse the gallery image url to uri
//            Uri savedImageURI = Uri.parse(picturePath);
//            // Display the saved image to ImageView
//            imageView.setImageURI(savedImageURI);
//            imageView.setImageDrawable(Drawable.createFromPath(picturePath));

            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            // программное управление режимом масштвьирования
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(imageView, "Ошибка чтения файла изображения",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", v -> {
            });
            snackbar.show();
        } // try-catсh
    }

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
            case ANIMAL_ACTIVITY:
                _ACTIVITY:
                animal = data.getParcelableExtra(Animal.class.getCanonicalName());
                showAnimal(animal);
                break;
        } // switch
    }
    //endregion
}