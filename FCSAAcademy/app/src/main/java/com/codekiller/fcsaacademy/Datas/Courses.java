package com.codekiller.fcsaacademy.Datas;

public class Courses {
    int image;
    String title, text;

    public Courses(int image, String title, String text) {
        this.image = image;
        this.title = title;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
