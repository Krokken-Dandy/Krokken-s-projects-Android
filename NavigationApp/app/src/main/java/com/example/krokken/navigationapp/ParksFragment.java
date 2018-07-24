package com.example.krokken.navigationapp;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

public class ParksFragment extends Fragment {

    private int parksPrimaryColorID = R.color.primary_park_color;
    private int parksPopupColorID = R.color.popup_park_color;
    private ArrayList<List> parksList;
    private PopupWindow popupWindow;

    public ParksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources res = getResources();

        //All of the images used as icons for the parks
        int[] parksImages = {R.drawable.ic_arapahoe_park, R.drawable.ic_canyon_park,
                R.drawable.ic_christiansen_park, R.drawable.ic_columbine_park,
                R.drawable.ic_eben_park, R.drawable.ic_elks_park, R.drawable.ic_foothills_park,
                R.drawable.ic_greenleaf_park};

        //The names of all the parks
        String[] parksNames = res.getStringArray(R.array.parks_names);

        //Addresses for all the parks
        String[] parksAddresses = res.getStringArray(R.array.parks_addresses);

        //Operating hours for each park
        String[] parksOperatingHours = res.getStringArray(R.array.parks_hours);

        //Phone numbers for each park
        String[] parksPhoneNumbers = res.getStringArray(R.array.parks_phone_numbers);

        //Websites for each park
        String[] parksWebsites = res.getStringArray(R.array.parks_websites);

        //Descriptions of the parks
        String[] parksDescriptions = res.getStringArray(R.array.parks_descriptions);

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        parksList = new ArrayList<List>();

        for (int i = 0; i < parksImages.length; i++) {
            parksList.add(new List(parksNames[i], parksImages[i], parksAddresses[i],
                    parksOperatingHours[i], parksPhoneNumbers[i], parksWebsites[i],
                    parksDescriptions[i]));
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(), parksList, parksPrimaryColorID);
        final ListView listView = rootView.findViewById(R.id.list);
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

    //Creates popup when list item is clicked for additional info
    public void locationPopup(View anchorView, int position) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        popupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        final List list = parksList.get(position);

        TextView locationName = popupView.findViewById(R.id.popup_location_name);
        ImageView locationIcon = popupView.findViewById(R.id.popup_location_icon);
        TextView locationAddress = popupView.findViewById(R.id.popup_location_address);
        TextView locationHours = popupView.findViewById(R.id.popup_location_operating_hours);
        TextView locationPhone = popupView.findViewById(R.id.popup_location_phone_number);
        TextView locationWebsite = popupView.findViewById(R.id.popup_location_website);
        TextView locationDescription = popupView.findViewById(R.id.popup_location_description);
        FrameLayout popupFrame = popupView.findViewById(R.id.popup_frame);

        final String mLocationAddress = list.getLocationAddress();
        final String mLocationPhone = list.getLocationPhoneNumber();
        final String mLocationWebsite = list.getLocationWebsite();
        String websiteText = getString(R.string.parks_website_text) + list.getLocationName();

        locationName.setText(list.getLocationName());
        locationIcon.setImageResource(list.getLocationIcon());
        locationAddress.setText(mLocationAddress);
        locationHours.setText(list.getLocationOperatingHours());
        locationPhone.setText(list.getLocationPhoneNumber());
        locationWebsite.setText(websiteText);
        locationDescription.setText(list.getLocationDescription());
        popupFrame.setBackgroundColor(getResources().getColor(parksPopupColorID));

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
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(mLocationAddress));
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, mapUri);
                startActivity(mapIntent);
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

        //Sets clickable on hikes popup to open an Intent with the trail map
        locationWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = list.visitBusinessWebsite(mLocationWebsite);
                startActivity(intent);
            }
        });
    }
}
