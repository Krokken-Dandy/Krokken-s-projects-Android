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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class BreweriesFragment extends Fragment {

    private int breweryPrimaryColorID = R.color.primary_brewery_color;
    private int breweryPopupColorID = R.color.popup_brewery_color;
    private ArrayList<List> breweryList;
    private PopupWindow popupWindow;

    public BreweriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Resources res = getResources();

        //All of the images used as icons for the breweries
        int[] breweriesImages = {R.drawable.ic_asher, R.drawable.ic_avery, R.drawable.ic_beyond,
                R.drawable.ic_boulder_beer, R.drawable.ic_bru, R.drawable.ic_cellar,
                R.drawable.ic_fate, R.drawable.ic_finkel_garf, R.drawable.ic_gunbarrel,
                R.drawable.ic_jwells, R.drawable.ic_kettle_spoke, R.drawable.ic_mountain_sun,
                R.drawable.ic_sanitas, R.drawable.ic_twisted_pine, R.drawable.ic_upslope,
                R.drawable.ic_vision_quest, R.drawable.ic_west_flanders, R.drawable.ic_wild_woods};

        //The names of all the breweries
        String[] breweriesNames = res.getStringArray(R.array.brewery_names);

        //Addresses for all the breweries
        String[] breweriesAddresses = res.getStringArray(R.array.brewery_addresses);

        //Operating hours for each breweries
        String[] breweriesOperatingHours = res.getStringArray(R.array.brewery_hours);

        //Phone numbers for each breweries
        String[] breweriesPhoneNumbers = res.getStringArray(R.array.brewery_phone_numbers);

        //Websites for each breweries
        String[] breweriesWebsites = res.getStringArray(R.array.brewery_websites);

        //Descriptions of the breweries
        String[] breweriesDescriptions = res.getStringArray(R.array.brewery_descriptions);

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        breweryList = new ArrayList<List>();

        for (int i = 0; i < breweriesImages.length; i++) {
            breweryList.add(new List(breweriesNames[i], breweriesImages[i], breweriesAddresses[i],
                    breweriesOperatingHours[i], breweriesPhoneNumbers[i], breweriesWebsites[i],
                    breweriesDescriptions[i]));
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(),
                breweryList, breweryPrimaryColorID);
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
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        final List list = breweryList.get(position);

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

        locationName.setText(list.getLocationName());
        locationIcon.setImageResource(list.getLocationIcon());
        locationAddress.setText(mLocationAddress);
        locationHours.setText(list.getLocationOperatingHours());
        locationPhone.setText(list.getLocationPhoneNumber());
        locationWebsite.setText(list.getLocationWebsite());
        locationDescription.setText(list.getLocationDescription());
        popupFrame.setBackgroundColor(getResources().getColor(breweryPopupColorID));

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

        //Sets clickable for trail map to open an Intent with the trail map
        locationWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = list.visitBusinessWebsite(mLocationWebsite);
                startActivity(intent);
            }
        });
    }
}
