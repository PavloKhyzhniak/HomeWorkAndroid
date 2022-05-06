package com.example.homeworkandroid.homework009.api;

import com.example.homeworkandroid.homework009.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_jsonplaceholder_Albums {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("albums")
    Call<List<Album>> getAlbums();

}
