package com.example.krokken.musicalapp;

public class Settings {

    private boolean mRandomPlay;
    private boolean mContinuousPlay;

    public void setRandomPlay(boolean random) {
        mRandomPlay = random;
    }

    public boolean getRandomPlay() {
        return mRandomPlay;
    }

    public void setContinuousPlay(boolean continuous) {
        mContinuousPlay = continuous;
    }

    public boolean getContinuousPlay() {
        return mContinuousPlay;
    }

}
