package com.example.happyfridayimagegrid;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class LoadImages extends AsyncTask<Void, Void, ArrayList<Image>>{

    ArrayList<Image> images;

    @Override
    protected void onPreExecute() {
        images = new ArrayList<>();

    }

    @Override
    protected ArrayList<Image> doInBackground(Void ... voids) {

        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/";

        File file = new File(directory);
        File[] files = file.listFiles();
        if(files != null){
            for(File f : files){ // loop and print all file
                String fileName = f.getName(); // this is file name
                images.add(new Image(fileName, directory + fileName));
            }
        }
        return images;
    }


    protected void onProgressUpdate(Image images) {
        //If a new image in directory
        //add the image to the image list
        //notify

    }

    @Override
    protected void onPostExecute(ArrayList<Image> i) {

    }

}
