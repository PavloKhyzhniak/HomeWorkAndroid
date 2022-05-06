package com.example.homeworkandroid.homework009.api;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.homeworkandroid.homework009.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static void GetAlbums()
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
                String[] postTitles = new String[collection.size()];


//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                        R.layout.item, postTitles);
//                postsList.setAdapter(adapter);
//                postsList.setOnItemClickListener(MainActivity.this);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }

        });
    }

}
