package com.example.krokken.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView profilePicture = findViewById(R.id.profile_picture);
        TextView name = findViewById(R.id.name);
        TextView birthday = findViewById(R.id.birthday);
        TextView country = findViewById(R.id.country);

        profilePicture.setImageResource(R.drawable.ic_launcher_background);
        name.setText("Christov");
        birthday.setText("November 26th");
        country.setText("United States");
    }
}
