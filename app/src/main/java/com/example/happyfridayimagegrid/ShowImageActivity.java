package com.example.happyfridayimagegrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import static com.example.happyfridayimagegrid.ImageRecyclerViewAdapter.EXTRA_IMAGE;


public class ShowImageActivity extends AppCompatActivity {
    private String imageFilePath;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        mImageView = findViewById(R.id.image_view_full);

        //Receives image object from the gallery
        imageFilePath = getIntent().getStringExtra(EXTRA_IMAGE);

        if(null != imageFilePath){

            Glide.with(this)
                    .load(imageFilePath)
                    .error(R.mipmap.ic_launcher_round)
                    .into(mImageView);
        }

    }

}
