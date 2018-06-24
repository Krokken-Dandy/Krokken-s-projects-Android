package com.example.krokken.navigationapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Displays the opening image
    private ImageView mainImage;

    //Mediaplayer for beginning soundclip
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    //Used by the imageview to transition to next activity
    private Animation fadeOut;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
            mainImage.startAnimation(fadeOut);
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if ((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) && mediaPlayer != null) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainImage = findViewById(R.id.main_image);
        mainImage.setImageResource(R.drawable.krokken_app_main);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        releaseMediaPlayer();
        fadeListeners();
        int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mediaPlayer = MediaPlayer.create(this, R.raw.krokkensapp);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }
        if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            mediaPlayer.release();

            // Set the media player back to null.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    //Creates the animation listeners for fade out to next activity
    public void fadeListeners() {
        fadeOut = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.fade_out);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mainImage.setVisibility(View.GONE);
                Intent layoutActivityIntent = new Intent(MainActivity.this, LayoutActivity.class);
                startActivity(layoutActivityIntent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}