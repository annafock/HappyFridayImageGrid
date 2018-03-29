package com.example.happyfridayimagegrid;

public class Image {
    String imageName;
    String filePath;
    String altText;

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

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }
}
