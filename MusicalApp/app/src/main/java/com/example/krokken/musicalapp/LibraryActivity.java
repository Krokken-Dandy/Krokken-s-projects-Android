package com.example.krokken.musicalapp;

import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {


    ArrayList<Library> musicLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        final ListView listView = findViewById(R.id.library_list_view);
        musicLibrary = new ArrayList<>();
        int size = 0;
        int[] media = {MediaStore.Audio.Playlists};
        for (int i = 0; i < size; i++) {
            musicLibrary.add(new Library("a", "b"));
        }

        final LibraryAdapter libraryAdapter = new LibraryAdapter(this, musicLibrary);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(LibraryActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(libraryAdapter);
    }
/*public static NotificationCompat.Builder from(
        Context context, MediaSession mediaSession) {
    MediaControllerCompat controller = mediaSession.getController();
    MediaMetadataCompat mediaMetaData = controller.getMetadata();
    MediaDescriptionCompat description = mediaMetaData.getDescription();

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
    builder
            .setContentTitle(description.getTitle())
            .setColor(ContextCompat.getColor(this, R.color.CHOOSECOLOR));
            .setContentText(description.getSubtitle())
            .setSubText(description.getDescription())
            .setLargeIcon(description.getIconBitmap())
            .setContentIntent(controller.getSessionActivity())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setDeleteIntent(getActionIntent(context, KeyEvent.KEYCODE_MEDIA_STOP));
    return builder;
}*/
//github.com/googlesamples/android-UniversalMusicPlayer


    /*AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        am.abandonAudioFocus(afChangeListener);
                    } else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, because something else is also
                        // playing audio over you.
                        // i.e. for notifications or navigation directions
                        // Depending on your audio playback, you may prefer to
                        // pause playback here instead. You do you.
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                    }
                }
            };*/
}

//NotificationCompat.MediaStyle
//API 14+ 3 actions, 16+ 5 buttons
//MediaMetaData
//AudioManager
