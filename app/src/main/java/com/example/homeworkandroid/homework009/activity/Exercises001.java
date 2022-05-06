package com.example.homeworkandroid.homework009.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework008.adapters.MessagesAdapter;
import com.example.homeworkandroid.homework008.models.CollectionMessage;
import com.example.homeworkandroid.homework008.models.Message;
import com.example.homeworkandroid.homework009.api.API_jsonplaceholder_Albums;
import com.example.homeworkandroid.homework009.api.API_jsonplaceholder_Photos;
import com.example.homeworkandroid.homework009.models.Album;
import com.example.homeworkandroid.homework009.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Exercises001 extends AppCompatActivity implements AdapterView.OnItemClickListener{

    final String LOG_TAG = "myLogs";

    private Button btnReturnToMain,
            btnAlbums, btnPhotos;

    // элемент отображения списка
    ListView lsvItemsList;
    ArrayAdapter<Album> itemsAdapterAlbum;
    ArrayAdapter<Photo> itemsAdapterPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework009_activity_exercises001);


        findViews();

        setListeners();

    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnAlbums = findViewById(R.id.btnAlbums);
        btnPhotos = findViewById(R.id.btnPhotos);

        lsvItemsList = findViewById(R.id.lsvItemsList);
    }

    private void setListeners() {
        btnReturnToMain.setOnClickListener(this::OnClickListener);

        btnAlbums.setOnClickListener(this::OnClickListener);
        btnPhotos.setOnClickListener(this::OnClickListener);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView txv = (TextView) view;
        Toast.makeText(this, txv.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void GetAlbums()
    {
        Retrofit retrofitAPI_jsonplaceholder_Albums = new Retrofit.Builder()
                .baseUrl(API_jsonplaceholder_Albums.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_jsonplaceholder_Albums API_jsonplaceholder_Albums = retrofitAPI_jsonplaceholder_Albums.create(API_jsonplaceholder_Albums.class);

        Call<List<Album>> call = API_jsonplaceholder_Albums.getAlbums();

        call.enqueue(new Callback<List<Album>>() {

            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                List<Album> collection = response.body();
                String[] titles = new String[collection.size()];
                int i = 0;

                for (Album item : collection) {
                    titles[i] =  item.getTitle();
                    i++;
                } // for i

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, titles);
                lsvItemsList.setAdapter(adapter);
//                itemsAdapterAlbum = new AlbumsAdapter(this, R.layout.homework009_album_item,
//                        collection);
//                lsvItemsList.setAdapter(itemsAdapterAlbum);

                // назначить обработчка клика на элемент списка
//                lsvItemsList.setOnItemClickListener(Exercises001.this);
                lsvItemsList.setOnItemClickListener((adapterView, view, position, l) -> {
                    // получить ссылку на выбранный комментарий
                    Album item = collection.get(position);
                    String body = item.getTitle() + "\n------------------------\n" +
                            item.getUserID();
                    // вывести название и тело комментария
                    Toast
                            .makeText(getApplicationContext(), body, Toast.LENGTH_LONG)
                            .show();
                });
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getClass().toString() + ": " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    public void GetPhotos()
    {
        Retrofit retrofitAPI_jsonplaceholder_Photos = new Retrofit.Builder()
                .baseUrl(API_jsonplaceholder_Photos.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_jsonplaceholder_Photos API_jsonplaceholder_Photos = retrofitAPI_jsonplaceholder_Photos.create(API_jsonplaceholder_Photos.class);

        Call<List<Photo>> call = API_jsonplaceholder_Photos.getPhotos();

        call.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                List<Photo> collection = response.body();
                String[] titles = new String[collection.size()];
                int i = 0;

                for (Photo item : collection) {
                    titles[i] =  item.getTitle();
                    i++;
                } // for i

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, titles);
                lsvItemsList.setAdapter(adapter);
//                itemsAdapterPhoto = new PhotosAdapter(this, R.layout.homework009_photo_item,
//                        collection);
//                lsvItemsList.setAdapter(itemsAdapterPhoto);
                lsvItemsList.setOnItemClickListener(Exercises001.this);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getClass().toString() + ": " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
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
            case  R.id.btnAlbums:
                GetAlbums();
                break;
            case  R.id.btnPhotos:
                GetPhotos();
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

}