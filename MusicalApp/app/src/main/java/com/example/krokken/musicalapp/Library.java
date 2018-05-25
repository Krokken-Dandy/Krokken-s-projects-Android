package com.example.krokken.musicalapp;

import android.graphics.Bitmap;

public class Library {

    private Bitmap image;
    private String title;
    private String audioResourceId;
    private String mDurationString;
    private long mDurationLong;

    public Library(String a, Bitmap b, String c, String duration) {
        title = a;
        image = b;
        audioResourceId = c;
        mDurationLong = Long.parseLong(duration);
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getAudioResourceId() {
        return audioResourceId;
    }

    public String getDuration() {
        mDurationString = convertDuration(mDurationLong);
        return mDurationString;
    }

    public String convertDuration(long duration) {
        String durationString = "";
        long hours;
        try {
            hours = (duration / 3600000);
        } catch (Exception e) {
            e.printStackTrace();
            return durationString;
        }
        long remaining_minutes = (duration - (hours * 3600000)) / 60000;
        String minutes = String.valueOf(remaining_minutes);
        if (minutes.equals(0)) {
            minutes = "00";
        }
        long remaining_seconds = (duration - (hours * 3600000) - (remaining_minutes * 60000));
        String seconds = String.valueOf(remaining_seconds);
        if (seconds.length() < 2) {
            seconds = "00";
        } else {
            seconds = seconds.substring(0, 2);
        }

        if (hours > 0) {
            durationString = hours + ":" + minutes + ":" + seconds;
        } else {
            durationString = minutes + ":" + seconds;
        }
        return durationString;
    }
}
