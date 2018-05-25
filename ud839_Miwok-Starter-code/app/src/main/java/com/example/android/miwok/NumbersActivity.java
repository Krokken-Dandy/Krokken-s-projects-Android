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

public class NumbersActivity extends AppCompatActivity {

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
        numbersArrayList();

    }

    //ArrayList for the Numbers in Miwok
    public void numbersArrayList() {
        final ArrayList<Word> numbersForMiwok = new ArrayList<Word>();

        //numbersForMiwok.add("One");
        numbersForMiwok.add(new Word("one", "lutti",
                R.drawable.number_one, R.raw.number_one));
        numbersForMiwok.add(new Word("two", "otiiko",
                R.drawable.number_two, R.raw.number_two));
        numbersForMiwok.add(new Word("three", "tolookosu",
                R.drawable.number_three, R.raw.number_three));
        numbersForMiwok.add(new Word("four", "oyyisa",
                R.drawable.number_four, R.raw.number_four));
        numbersForMiwok.add(new Word("five", "massokka",
                R.drawable.number_five, R.raw.number_five));
        numbersForMiwok.add(new Word("six", "temmokka",
                R.drawable.number_six, R.raw.number_six));
        numbersForMiwok.add(new Word("seven", "kenekaku",
                R.drawable.number_seven, R.raw.number_seven));
        numbersForMiwok.add(new Word("eight", "kawinta",
                R.drawable.number_eight, R.raw.number_eight));
        numbersForMiwok.add(new Word("nine", "wo'e",
                R.drawable.number_nine, R.raw.number_nine));
        numbersForMiwok.add(new Word("ten", "na'aacha",
                R.drawable.number_ten, R.raw.number_ten));

        WordAdapter numberAdapter = new WordAdapter(this, numbersForMiwok, R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(numberAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numbersForMiwok.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
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
