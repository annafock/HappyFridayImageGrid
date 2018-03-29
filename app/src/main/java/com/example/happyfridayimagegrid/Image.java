package com.example.happyfridayimagegrid;

public class Image {
    private final String imageName;
    private final String filePath;

    public Image(String imageName, String filePath) {
        this.imageName = imageName;
        this.filePath = filePath;
    }

    public String getImageName() {
        return imageName;
    }

    public String getFilePath() {
        return filePath;
    }


}
