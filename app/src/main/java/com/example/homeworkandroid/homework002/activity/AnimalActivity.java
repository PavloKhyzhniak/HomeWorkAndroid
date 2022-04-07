package com.example.homeworkandroid.homework002.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework002.MainActivityHW002;
import com.example.homeworkandroid.homework002.models.Animal;
import com.example.homeworkandroid.homework002.models.AnimalType;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class AnimalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;

    private Animal animal;

    // приращение возраста
    private int incAge;
    private AnimalType currentAnimalType;

    private RadioGroup rgrType;
    private Button btnLoadImage;
    private Button btnReturnToHomeWork002, btnReturnToMain;
    private Button btnAnimalActivityProcess, btnAnimalActivityBack;
    private EditText edtAnimalBreed, edtAnimalName, edtAnimalWeight, edtAnimalLastName, edtAnimalFirstName;
    private TextView txvAnimalType, txvAnimalAge;
    private ImageView imageViewImg;
    private ImageView imageViewTypeIcon;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework002_activity_animal);

        // получить параметр из вызывающей активности
        Intent intent = getIntent();
        animal = intent.getParcelableExtra(Animal.class.getCanonicalName());

        findViews();

        setListeners();

        setDefault();

        showAnimal(animal);
        currentAnimalType = animal.getType();

        createRadioButton(rgrType, currentAnimalType);
        fillSpinner(spinner, currentAnimalType);
    }

    private void setDefault() {
        // установить incAge и соответсвующую радиокнопку
        incAge = 1;
        ((RadioButton) findViewById(R.id.rbtAgeInc1)).setChecked(true);
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        edtAnimalBreed = findViewById(R.id.edtAnimalBreed);
        edtAnimalName = findViewById(R.id.edtAnimalName);
        txvAnimalAge = findViewById(R.id.txvAnimalAge);
        edtAnimalWeight = findViewById(R.id.edtAnimalWeight);
        edtAnimalLastName = findViewById(R.id.edtAnimalLastName);
        edtAnimalFirstName = findViewById(R.id.edtAnimalFirstName);
        txvAnimalType = findViewById(R.id.txvAnimalType);

        imageViewImg = findViewById(R.id.imageViewImg);
        imageViewTypeIcon = findViewById(R.id.imageViewTypeIcon);

        btnReturnToHomeWork002 = findViewById(R.id.btnReturnToHomeWork002);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnLoadImage = findViewById(R.id.btnLoadPicture);

        btnAnimalActivityProcess = findViewById(R.id.btnAnimalActivityProcess);
        btnAnimalActivityBack = findViewById(R.id.btnAnimalActivityBack);

        rgrType = findViewById(R.id.rgrType);
        spinner = findViewById(R.id.spinnerType);
    }

    private void setListeners() {
        btnReturnToHomeWork002.setOnClickListener(v -> returnToHomeWork002());
        btnReturnToMain.setOnClickListener(v -> returnToMain());

        btnAnimalActivityProcess.setOnClickListener(v -> processAnimalActivity());
        btnAnimalActivityBack.setOnClickListener(v -> backAnimalActivity());

        btnLoadImage.setOnClickListener(arg0 -> {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        });

        // установка обработчика события
        ((RadioGroup) findViewById(R.id.rgrType))
                .setOnCheckedChangeListener(this::onTypeRBchanged);

    }

    private void onTypeRBchanged(RadioGroup group, int checkedId) {
        AnimalType[] arr = AnimalType.values();
        int cnt = arr.length;
        if (checkedId - 100 <= cnt)
            currentAnimalType = arr[checkedId - 100];
    }

    private void createRadioButton(RadioGroup rg, AnimalType type) {
        //RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.HORIZONTAL
        AnimalType[] arr = AnimalType.values();
        int cnt = arr.length;
        RadioButton[] rb = new RadioButton[cnt];

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < cnt; i++) {
            rb[i] = new RadioButton(this);
            rb[i].setText(arr[i].getName());
            rb[i].setId(i + 100);

            if (arr[i].equals(type))
                rb[i].setChecked(true);

            rb[i].setLayoutParams(lp);
            rg.addView(rb[i], 0, lp);
        }
        //ll.addView(rg);//you add the whole RadioGroup to the layout
    }

    private void fillSpinner(Spinner spinner, AnimalType type) {
        AnimalType[] arr = AnimalType.values();
        int cnt = arr.length;

        String[] paths = new String[cnt];
        int selected = 0;
        for (int i = 0; i < cnt; i++) {
            paths[i] = arr[i].getName();

            if (arr[i].equals(type))
                selected = i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AnimalActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(selected);
    }


    private void processAnimalActivity() {
        animal.setAge(animal.getAge() + incAge);
        animal.setType(currentAnimalType);

        animal.setBreed(edtAnimalBreed.getText().toString());
        animal.setName(edtAnimalName.getText().toString());
        animal.setLastNameOwner(edtAnimalLastName.getText().toString());
        animal.setFistNameOwner(edtAnimalFirstName.getText().toString());
        animal.setWeight(Integer.parseInt(edtAnimalWeight.getText().toString().trim()));

        showAnimal(animal);
    }

    private void backAnimalActivity() {
        Intent intent = new Intent();
        intent.putExtra(Animal.class.getCanonicalName(), animal);

        setResult(Exercises001.RESULT_OK, intent);
        finish();
    }

    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    private void returnToHomeWork002() {
        Intent myIntent = new Intent(this, MainActivityHW002.class);
        startActivity(myIntent);
    }

    // обработчик клика по радиокнопке из группы приращения возраста
    @SuppressLint("NonConstantResourceId")
    public void onClickAgeIncHandler(View view) {
        RadioButton rb = (RadioButton) view;

        // так можно выяснить отмечен или не отмечен RadioButton
        // boolean checked = rb.isChecked();

        switch (rb.getId()) {
            case R.id.rbtAgeInc1:
                incAge = +1;
                break;
            case R.id.rbtAgeDec1:
                incAge = -1;
                break;
        } // switch
    } // onClickAgeIncHandler

    private void showAnimal(Animal animal) {

        edtAnimalBreed.setText(animal.getBreed());
        edtAnimalName.setText(animal.getName());
        txvAnimalAge.setText(String.format(Locale.ROOT, "%3d", animal.getAge()));
        edtAnimalWeight.setText(String.format(Locale.ROOT, "%3d", animal.getWeight()));
        edtAnimalLastName.setText(animal.getLastNameOwner());
        edtAnimalFirstName.setText(animal.getFistNameOwner());
        txvAnimalType.setText(animal.getType().getName());

        if (animal.getImg().equals("")) {
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
        } // try-catch
    }

    private void ShowGalleryImg(ImageView imageView, String picturePath) {
        try {
            // Parse the gallery image url to uri
            Uri savedImageURI = Uri.parse(picturePath);
            // Display the saved image to ImageView
            imageView.setImageURI(savedImageURI);

//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            // программное управление режимом масштвьирования
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(imageView, "Ошибка чтения файла изображения",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", v -> {
            });
            snackbar.show();
        } // try-catch
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            animal.setImg(selectedImage.getPath());
//            cursor.close();
//            imageViewImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//        }

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                animal.setImg(getPath(selectedImageUri));
                Bitmap finalBitmap = BitmapFactory.decodeFile(animal.getImg());
                imageViewImg.setImageBitmap(finalBitmap);
                // Set the image in ImageView
                findViewById(R.id.imageViewImg).post(() -> ((ImageView) findViewById(R.id.imageViewImg)).setImageURI(selectedImageUri));
                //imageViewImg.setImageBitmap(BitmapFactory.decodeFile(getPath(selectedImageUri)));
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long checkedId) {
        AnimalType[] arr = AnimalType.values();

        currentAnimalType = arr[(int) checkedId];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}