package com.example.homeworkandroid.homework009.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Config;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework009.adapters.AlbumsAdapter;
import com.example.homeworkandroid.homework009.adapters.PhotosAdapter;
import com.example.homeworkandroid.homework009.api.API_jsonplaceholder_Albums;
import com.example.homeworkandroid.homework009.api.API_jsonplaceholder_Photos;
import com.example.homeworkandroid.homework009.models.Album;
import com.example.homeworkandroid.homework009.models.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Exercises001 extends AppCompatActivity implements AdapterView.OnItemClickListener {

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

        mRequestQueue = Volley.newRequestQueue(this);// очередь запросов

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

    public void GetAlbums() {
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
//                String[] titles = new String[collection.size()];
//                int i = 0;
//
//                for (Album item : collection) {
//                    titles[i] =  item.getTitle();
//                    i++;
//                } // for i

//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1, titles);
//                lsvItemsList.setAdapter(adapter);
                itemsAdapterAlbum = new AlbumsAdapter(getApplicationContext(), R.layout.homework009_album_item,
                        collection);
                lsvItemsList.setAdapter(itemsAdapterAlbum);

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

    private List<Album> getAlbums(JSONArray j) {
        List<Album> collection = new ArrayList<>();
        for (int i = 0; i < j.length(); i++) {
            try {
                JSONObject json = j.getJSONObject(i);

                Album new_album = new Album();
                new_album.setTitle(json.getString(Album.TAG_TITLE));
                new_album.setID(json.getInt(Album.TAG_ID));
                new_album.setUserID(json.getInt(Album.TAG_USERID));

                collection.add(new_album);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return collection;
    }

    private RequestQueue mRequestQueue; // очередь запросов

    public void GetAlbumsVolley() {
        String url = "https://jsonplaceholder.typicode.com/albums/"; //url, из которого мы будем брать JSON-объект

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, //GET - API-запрос для получение данных
                url, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Album> collection = getAlbums(response);
//                String[] titles = new String[collection.size()];
//                int i = 0;
//
//                for (Album item : collection) {
//                    titles[i] =  item.getTitle();
//                    i++;
//                } // for i

//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1, titles);
//                lsvItemsList.setAdapter(adapter);
                itemsAdapterAlbum = new AlbumsAdapter(getApplicationContext(), R.layout.homework009_album_item,
                        collection);
                lsvItemsList.setAdapter(itemsAdapterAlbum);

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
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request); // добавляем запрос в очередь
    }

    public void GetAlbumsVolleyByID(long id) {
        String url = "https://jsonplaceholder.typicode.com/albums/" + String.format(Locale.UK, "%d/", id); //url, из которого мы будем брать JSON-объект

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, //GET - API-запрос для получение данных
                url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Album> collection = new ArrayList<>();

                Album new_album = new Album();
                try {
                    new_album.setTitle(response.getString(Album.TAG_TITLE));
                    new_album.setID(response.getInt(Album.TAG_ID));
                    new_album.setUserID(response.getInt(Album.TAG_USERID));

                    collection.add(new_album);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                String[] titles = new String[collection.size()];
//                int i = 0;
//
//                for (Album item : collection) {
//                    titles[i] =  item.getTitle();
//                    i++;
//                } // for i

//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1, titles);
//                lsvItemsList.setAdapter(adapter);
                itemsAdapterAlbum = new AlbumsAdapter(getApplicationContext(), R.layout.homework009_album_item,
                        collection);
                lsvItemsList.setAdapter(itemsAdapterAlbum);

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
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request); // добавляем запрос в очередь
    }

    public void GetPhotos() {
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
//                String[] titles = new String[collection.size()];
//                int i = 0;
//
//                for (Photo item : collection) {
//                    titles[i] =  item.getTitle();
//                    i++;
//                } // for i

//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1, titles);
//                lsvItemsList.setAdapter(adapter);
                itemsAdapterPhoto = new PhotosAdapter(getApplicationContext(), R.layout.homework009_photo_item,
                        collection);
                lsvItemsList.setAdapter(itemsAdapterPhoto);
//                lsvItemsList.setOnItemClickListener(Exercises001.this);
                lsvItemsList.setOnItemClickListener((adapterView, view, position, l) -> {
                    // получить ссылку на выбранный комментарий
                    Photo item = collection.get(position);
                    String body = item.getTitle() + "\n------------------------\n" +
                            item.getID();
                    // вывести название и тело комментария
                    Toast
                            .makeText(getApplicationContext(), body, Toast.LENGTH_LONG)
                            .show();
                });
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getClass().toString() + ": " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void SendImage(final String image) {
//        Uri filePath = data.getData();
//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//            Bitmap lastBitmap = null;
//            lastBitmap = bitmap;
//            //encoding image to string
//            String image = getStringImage(lastBitmap);
//            Log.d("image",image);
//            //passing the image to volley
//            SendImage(image);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "URL",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("uploade", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();

                params.put("image", image);
                return params;
            }
        };
        {
            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
//        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
            mRequestQueue.add(stringRequest);
        }
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
            case R.id.btnAlbums:
//                GetAlbums();
//                GetAlbumsVolley();
                GetAlbumsVolleyByID(2);
                break;
            case R.id.btnPhotos:
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