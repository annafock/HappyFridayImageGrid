package com.example.happyfridayimagegrid;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_image);
        int numberOfColumns = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        ArrayList<Image> files = listAllFiles();

        adapter = new ImageRecyclerViewAdapter(this, files);

        mRecyclerView.setAdapter(adapter);

    }

    private ArrayList<Image> listAllFiles(){
        ArrayList<Image> filenames = new ArrayList<>();
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/";

        File file = new File(directory);
        File[] files = file.listFiles();
        if(files != null){
            for(File f : files){ // loop and print all file
                String fileName = f.getName(); // this is file name
                filenames.add(new Image(fileName, directory + fileName));
                System.out.println(fileName);
            }
        }
        return filenames;
    }

}
