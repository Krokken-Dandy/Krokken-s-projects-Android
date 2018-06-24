package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {
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

    public FamilyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> familyForMiwok = new ArrayList<Word>();

        //numbersForMiwok.add("One");
        familyForMiwok.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyForMiwok.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyForMiwok.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyForMiwok.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyForMiwok.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyForMiwok.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyForMiwok.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyForMiwok.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyForMiwok.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyForMiwok.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter numberAdapter = new WordAdapter(getActivity(), familyForMiwok, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(numberAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = familyForMiwok.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

                if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) releaseMediaPlayer();
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
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
