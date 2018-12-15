package com.example.c052735.simplephotoview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.c052735.simplephotoview.remote.RemoteDateSources;
import com.example.c052735.simplephotoview.remote.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    List<Photo> photosList = new ArrayList<>();
    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        pullToRefresh = findViewById(R.id.pullRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

    }

    private void getData() {
        Call<List<Photo>> photos = RemoteDateSources.getPhotos();
        photos.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                photosList = response.body();
                initView();


            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("Main", "onFailure: ");
            }
        });
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        PhotoAdapter adapter = new PhotoAdapter(photosList);
        recyclerView.setAdapter(adapter);
    }
}
