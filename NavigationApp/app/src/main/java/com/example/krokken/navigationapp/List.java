package com.example.krokken.navigationapp;

import android.content.Intent;
import android.net.Uri;

public class List {
    private String locationName;

    private int locationIcon = NO_IMAGE_PROVIDED;

    private int locationTrailMapImage;

    private String locationAddress;

    private String locationOperatingHours;

    private String locationPhoneNumber;

    private String locationWebsite;

    private String locationDescription;

    private static final int NO_IMAGE_PROVIDED = -1;

    public List(String locationName, int locationIcon, String locationAddress,
                String locationHours, String locationPhone, String locationWebsite,
                String locationDescription) {
        this.locationName = locationName;
        this.locationIcon = locationIcon;
        this.locationAddress = locationAddress;
        locationOperatingHours = locationHours;
        locationPhoneNumber = locationPhone;
        this.locationWebsite = locationWebsite;
        this.locationDescription = locationDescription;
    }

    public List(String locationName, int locationIcon, int locationTrailMap, String locationAddress,
                String locationHours, String locationPhone, String locationTrailMapNames,
                String locationDescription) {
        this.locationName = locationName;
        this.locationIcon = locationIcon;
        locationTrailMapImage = locationTrailMap;
        this.locationAddress = locationAddress;
        locationOperatingHours = locationHours;
        locationPhoneNumber = locationPhone;
        locationWebsite = locationTrailMapNames;
        this.locationDescription = locationDescription;
    }

    public int getLocationIcon() {
        return locationIcon;
    }

    public int getTrailMapImage() {
        return locationTrailMapImage;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationOperatingHours() {
        return locationOperatingHours;
    }

    public String getLocationPhoneNumber() {
        return locationPhoneNumber;
    }

    public String getLocationWebsite() {
        return locationWebsite;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public boolean hasImage() {
        return locationIcon != NO_IMAGE_PROVIDED;
    }

    //Method to input business phone number for user to dial
    public Intent callBusiness(String phone) {
        String uri = "tel:" + phone.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        return intent;
    }

    //Method used by fragments to open intent for directions
    public Intent openMap(String map) {
        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(map));
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, mapUri);
        return mapIntent;
    }

    //Method used by fragments to visit business website
    public Intent visitBusinessWebsite(String website) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + website));
        return browserIntent;
    }
}
