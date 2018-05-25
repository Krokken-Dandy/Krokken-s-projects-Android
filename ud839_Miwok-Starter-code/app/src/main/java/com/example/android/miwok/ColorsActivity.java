package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if ((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) && mMediaPlayer != null) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        colorsArrayList();

    }

    //ArrayList for the Numbers in Miwok
    public void colorsArrayList() {
        final ArrayList<Word> colorsForMiwok = new ArrayList<Word>();

        //numbersForMiwok.add("One");
        colorsForMiwok.add(new Word("red", "weṭeṭṭi",
                R.drawable.color_red, R.raw.color_red));
        colorsForMiwok.add(new Word("green", "chokokki",
                R.drawable.color_green, R.raw.color_green));
        colorsForMiwok.add(new Word("brown", "ṭakaakki",
                R.drawable.color_brown, R.raw.color_brown));
        colorsForMiwok.add(new Word("gray", "ṭopoppi",
                R.drawable.color_gray, R.raw.color_gray));
        colorsForMiwok.add(new Word("black", "kululli",
                R.drawable.color_black, R.raw.color_black));
        colorsForMiwok.add(new Word("white", "kelelli",
                R.drawable.color_white, R.raw.color_white));
        colorsForMiwok.add(new Word("dusty yellow", "ṭopiisә",
                R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsForMiwok.add(new Word("mustard yellow", "chiwiiṭә",
                R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter numberAdapter = new WordAdapter(this, colorsForMiwok, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(numberAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = colorsForMiwok.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

                if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) releaseMediaPlayer();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}



