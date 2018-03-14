package com.example.krokken.cookies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krokken.cookies.R;


public class MainActivity extends AppCompatActivity {

    String cookieText = "I'm so full";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void eatCookie(View view) {
        eatCookieText(cookieText);
        eatCookieImage();
    }

    /**
     * Called when the cookie should be eaten.
     */
    public void eatCookieText(String cookieText) {
        TextView eatCookieStatus = (TextView) findViewById(R.id.cookieStatus_text_view);
        eatCookieStatus.setText(String.valueOf(cookieText));
    }

    public void eatCookieImage(){
        ImageView cookieEatenImage = (ImageView) findViewById(R.id.android_cookie_image_view);
        cookieEatenImage.setImageResource(R.drawable.after_cookie);
    }
}