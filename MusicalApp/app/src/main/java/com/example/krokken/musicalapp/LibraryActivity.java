package com.example.krokken.musicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        final ListView gridview = findViewById(R.id.library_list_view);
        musicLibrary = new ArrayList<>();

        musicLibrary.add(new Library("a", "b"));
        final LibraryAdapter libraryAdapter = new LibraryAdapter(this, R.layout.library_list, musicLibrary);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(LibraryActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        gridview.setAdapter(libraryAdapter);
    }
}
