package com.example.krokken.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String businessPhoneNumber, businessName, businessDescription, businessEmailAddress,
            businessFacebook, businessWebpage;
    ImageView businessLogoImage, offerOne, phoneIcon, emailIcon, mapIcon, facebookIcon;
    TextView businessPhoneNumberText, businessNameText, businessDescriptionText, businessEmailText,
            businessWebpageText, businessAddressText, businessHoursText;
    int[] businessImages = {R.drawable.come_in, R.drawable.comic, R.drawable.action_figure,
            R.drawable.free_comic_day};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onClickListeners();
        initializeVariables();
        fadeListeners();
    }

    //Finds all views that will be used
    private void findViews() {
        businessLogoImage = findViewById(R.id.business_logo_image);
        offerOne = findViewById(R.id.offer_1);
        businessNameText = findViewById(R.id.business_name);
        businessDescriptionText = findViewById(R.id.business_description);
        businessPhoneNumberText = findViewById(R.id.phone_number_account);
        businessEmailText = findViewById(R.id.email_account);
        businessWebpageText = findViewById(R.id.website_account);
        businessAddressText = findViewById(R.id.business_address);
        phoneIcon = findViewById(R.id.phone_number_icon);
        emailIcon = findViewById(R.id.email_icon);
        mapIcon = findViewById(R.id.map_icon);
        facebookIcon = findViewById(R.id.facebook_icon);
        businessHoursText = findViewById(R.id.business_hours);
    }

    //Initializes variables and
    //Sets text and images of the business
    private void initializeVariables() {
        Resources res = getResources();
        AssetManager am = this.getApplicationContext().getAssets();
        Typeface asFont = Typeface.createFromAsset(am, String.format(Locale.US,
                "fonts/%s", "airstrike.ttf"));

        businessPhoneNumber = res.getString(R.string.phone_number);
        businessName = res.getString(R.string.business_name);
        businessEmailAddress = res.getString(R.string.business_email);
        businessFacebook = res.getString(R.string.facebook_page);
        businessWebpage = res.getString(R.string.business_website);
        businessDescription = res.getString(R.string.business_description);
        businessPhoneNumberText.setText(businessPhoneNumber);
        businessNameText.setText(businessName);
        businessNameText.setTypeface(asFont);
        businessEmailText.setText(businessEmailAddress);
        businessWebpageText.setText(businessWebpage.replace(getString(R.string.http),
                ""));
        businessDescriptionText.setText(businessDescription);
        businessAddressText.setText(R.string.business_address);
        businessHoursText.setText(R.string.business_operating_hours);
        phoneIcon.setImageResource(R.drawable.phone);
        emailIcon.setImageResource(R.drawable.email);
        facebookIcon.setImageResource(R.drawable.facebook);
        businessLogoImage.setImageResource(R.drawable.business_icon);
        mapIcon.setImageResource(R.drawable.map);
    }

    //Sets all onClickListeners to be used
    private void onClickListeners() {
        //Inputs phone number if user wants to call business
        businessPhoneNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBusiness();
            }
        });
        //Inputs business' email for user to contact
        businessEmailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailBusiness();
            }
        });
        //Opens google maps with directions to store
        businessAddressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapAddress();
            }
        });
        //Opens facebook page for business
        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookPage();
            }
        });
        //Opens business' webpage
        businessWebpageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitBusinessWebsite();
            }
        });
    }

    //Method to input business phone number for user to dial
    private void callBusiness() {
        String uri = "tel:" + businessPhoneNumber.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    //Method to email business
    private void emailBusiness() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + businessEmailAddress));

        startActivity(Intent.createChooser(emailIntent, "Send Email"));

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    //Method to open maps with directions to store
    private void mapAddress() {
        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(getString(R.string.business_address)));
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, mapUri);
        startActivity(mapIntent);
    }

    //Intent to open the official Facebook app. If the Facebook app is not installed then the
    //default web browser will be used.
    private void facebookPage() {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPage(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    //Is called by facebookPage() to see the best way to open facebook and returns it
    public String getFacebookPage(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo(
                    "com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + businessFacebook;
            } else { //older versions of fb app
                return "fb://page/" + businessFacebook;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return businessFacebook; //normal web url
        }
    }

    //Method to visit business website
    private void visitBusinessWebsite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(businessWebpage));
        startActivity(browserIntent);
    }

    //Creates the animation listeners for fade in and fade out to cycle the business' images
    public void fadeListeners() {
        final Animation fadeIn = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.fade_out);
        offerOne.setImageResource(businessImages[0]);
        offerOne.startAnimation(fadeIn);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                offerOne.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                i++;
                if (i >= businessImages.length) i = 0;

                offerOne.setImageResource(businessImages[i]);
                offerOne.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
