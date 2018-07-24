package com.example.android.quakereport;

public class Earthquake {

    double mMagnitude;

    String mLocationPart1;

    String mLocationPart2;

    String mEarthquakeWebsite;

    long mTime;

    public Earthquake(double magnitude, String locationPart1, String locationPart2, long time, String earthquakeWebsite) {
        mMagnitude = magnitude;
        mLocationPart1 = locationPart1;
        mLocationPart2 = locationPart2;
        mTime = time;
        mEarthquakeWebsite = earthquakeWebsite;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocationPart1() {
        return mLocationPart1;
    }

    public String getLocationPart2() {
        return mLocationPart2;
    }

    public long getTime() {
        return mTime;
    }

    public String getEarthquakeWebsite() {
        return mEarthquakeWebsite;
    }
}
