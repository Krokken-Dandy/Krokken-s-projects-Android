package com.example.krokken.musicalapp;

import android.graphics.Bitmap;

public class Library {

    private Bitmap image;
    private String title;


    public Library(String a, String b) {
        title = a;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getImage() {
        return image;
    }
}
