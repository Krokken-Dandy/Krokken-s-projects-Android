package com.example.krokken.navigationapp;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HikesFragment extends Fragment {

    private int hikesPrimaryColorID = R.color.primary_hike_color;
    private int hikesPopupColorID = R.color.popup_hike_color;
    private ArrayList<List> hikesList;
    private PopupWindow popupWindow;

    public HikesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources res = getResources();

        //All of the images used as icons for the hikes
        int[] hikesImages = {R.drawable.ic_first, R.drawable.ic_eldorado, R.drawable.ic_forsythe,
                R.drawable.ic_green_mountain, R.drawable.ic_mt_sanitas};

        //All of the trail maps that will be used in the secondary popup for the hikes
        int[] hikesTrailMaps = {R.drawable.first_flatiron_trail_map,
                R.drawable.eldorado_falls_trail_map, R.drawable.forsythe_trail_map,
                R.drawable.green_mountain_trail_map, R.drawable.sanitas_trail_map};

        //The names of all the breweries
        String[] hikesNames = res.getStringArray(R.array.hikes_names);

        //Addresses for all the breweries
        String[] hikesAddresses = res.getStringArray(R.array.hikes_addresses);

        //Operating hours for each breweries
        String[] hikesDifficultyDistance = res.getStringArray(R.array.hikes_hours);

        //Phone numbers for each breweries
        String[] hikesPhoneNumbers = res.getStringArray(R.array.hikes_phone_numbers);

        //Websites for each breweries
        String[] hikesTrailMapNames = res.getStringArray(R.array.hikes_trail_maps);

        //Descriptions of the breweries
        String[] hikesDescriptions = res.getStringArray(R.array.hikes_descriptions);

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        hikesList = new ArrayList<List>();

        for (int i = 0; i < hikesImages.length; i++) {
            hikesList.add(new List(hikesNames[i], hikesImages[i], hikesTrailMaps[i],
                    hikesAddresses[i], hikesDifficultyDistance[i], hikesPhoneNumbers[i],
                    hikesTrailMapNames[i], hikesDescriptions[i]));
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(), hikesList, hikesPrimaryColorID);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                locationPopup(view, position);
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void locationPopup(View anchorView, int position) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        popupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        final List list = hikesList.get(position);

        TextView locationName = popupView.findViewById(R.id.popup_location_name);
        ImageView locationIcon = popupView.findViewById(R.id.popup_location_icon);
        TextView locationAddress = popupView.findViewById(R.id.popup_location_address);
        TextView locationHours = popupView.findViewById(R.id.popup_location_operating_hours);
        TextView locationPhone = popupView.findViewById(R.id.popup_location_phone_number);
        TextView locationTrailMapName = popupView.findViewById(R.id.popup_location_website);
        TextView locationDescription = popupView.findViewById(R.id.popup_location_description);
        FrameLayout popupFrame = popupView.findViewById(R.id.popup_frame);

        final String mLocationAddress = list.getLocationAddress();
        final String mLocationPhone = list.getLocationPhoneNumber();

        locationName.setText(list.getLocationName());
        locationIcon.setImageResource(list.getLocationIcon());
        locationAddress.setText(R.string.hikes_directions_text);
        locationHours.setText(list.getLocationOperatingHours());
        locationPhone.setText(list.getLocationPhoneNumber());
        locationTrailMapName.setText(list.getLocationWebsite());
        locationDescription.setText(list.getLocationDescription());
        popupFrame.setBackgroundColor(getResources().getColor(hikesPopupColorID));

        //Popup window is focusable
        popupWindow.setFocusable(true);

        //Dismiss popup window when clicked outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        //Sets the animation for the popup window
        popupWindow.setAnimationStyle(R.style.AnimationPopup);

        //Set location of the popup to the center
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

        //Sets clickable for the address to open an Intent for google map directions
        locationAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = list.openMap(mLocationAddress);
                startActivity(intent);
            }
        });

        //Sets clickable to start Intent for user to call phone number
        locationPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = list.callBusiness(mLocationPhone);
                startActivity(intent);
            }
        });

        final View av = anchorView;
        final int pos = position;
        //Sets clickable for trail map to open an Intent with the trail map
        locationTrailMapName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trailMapPopup(av, pos);
            }
        });
    }

    public void trailMapPopup(View anchorView, int position) {

        View popupView = getLayoutInflater().inflate(R.layout.trail_map_popup, null);

        popupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        final List list = hikesList.get(position);
        ImageView trailMapImage = popupView.findViewById(R.id.popup_trailmap_image);

        trailMapImage.setImageResource(list.getTrailMapImage());

        //Popup window is focusable
        popupWindow.setFocusable(true);

        //Dismiss popup window when clicked outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        //Sets the animation for the popup window
        popupWindow.setAnimationStyle(R.style.AnimationPopup);

        //Set location of the popup to the center
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }
}
