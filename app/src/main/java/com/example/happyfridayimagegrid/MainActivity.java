package com.example.happyfridayimagegrid;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageRecyclerViewAdapter.OnItemClickListener,
                                                               LoadImagesCallback{
    private RecyclerView mRecyclerView;
    private ImageRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view_image);
        int numberOfColumns = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        LoadImages task = new LoadImages(this);
        task.execute();

    }


    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onImagesLoaded(ArrayList<Image> imagesArray) {
        adapter = new ImageRecyclerViewAdapter(this, imagesArray);

        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }
}
