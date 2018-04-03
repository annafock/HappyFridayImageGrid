package com.example.happyfridayimagegrid;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageRecyclerViewAdapter.OnItemClickListener,
        LoadImagesCallback{
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private ImageRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view_image);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        int numberOfColumns = 3;

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        LoadImages task = new LoadImages(this);
        task.execute();

    }

    // set grid layoutmanager depending on orientation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        }
    }

    @Override
    public void onItemClicked(int position) {
        //Is set in the adapter
    }

    //Called from onPostExecute and runs from UI thread
    @Override
    public void onImagesLoaded(ArrayList<Image> imagesArray) {

        progressBar.setVisibility(View.INVISIBLE);

        if (0 != imagesArray.size()){

            progressBar.setVisibility(View.GONE);

            adapter = new ImageRecyclerViewAdapter(this, imagesArray);

            mRecyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(this);
        }else{
            //TODO error message
        }
    }

    //Called from onProgressUpdate on background Thread
    @Override
    public void sendUpdate(int imageCount) {

        if (imageCount > 0){
            progressBar.setVisibility(View.VISIBLE);

            progressBar.setProgress(imageCount);

            //Slows the progress bar down just to make it visible in this example
            android.os.SystemClock.sleep(10);
        }

    }

}
