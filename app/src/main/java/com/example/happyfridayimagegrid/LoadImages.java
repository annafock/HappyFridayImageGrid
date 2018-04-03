package com.example.happyfridayimagegrid;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class LoadImages extends AsyncTask<Void, Integer, ArrayList<Image>>{

    ArrayList<Image> images;
    LoadImagesCallback loadImagesCallback;


    //Constructor for LoadImageCallBack interface
    public LoadImages(LoadImagesCallback aLoadImagesCallback){
        this.loadImagesCallback = aLoadImagesCallback;
    }

    @Override
    protected void onPreExecute() {

        images = new ArrayList<>();
    }

    @Override
    //runs on background thread
    protected ArrayList<Image> doInBackground(Void ... voids) {
        int imageCount = 0;
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/";

        File file = new File(directory);
        File[] files = file.listFiles();

        if(files != null){
            for(File f : files){ // loop and print all file
                String fileName = f.getName(); // this is file name
                images.add(new Image(fileName, directory + fileName));

                imageCount++;
                onProgressUpdate(imageCount);

            }
        }

        return images;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
     loadImagesCallback.sendUpdate(values[0]);

    }


    @Override
    //runs on UI thread
    protected void onPostExecute(ArrayList<Image> i) {
        loadImagesCallback.onImagesLoaded(images);

    }

}
