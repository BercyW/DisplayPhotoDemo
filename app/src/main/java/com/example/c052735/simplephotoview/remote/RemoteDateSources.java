package com.example.c052735.simplephotoview.remote;

import com.example.c052735.simplephotoview.remote.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RemoteDateSources {
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public static Retrofit create() {

       return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Call<List<Photo>> getPhotos() {
        Retrofit retrofit = create();
        RemoteService remoteService =retrofit.create(RemoteService.class);
        return remoteService.getPhotos();
    }

    public interface RemoteService{
        @GET("photos")
        Call<List<Photo>> getPhotos();
    }
}
