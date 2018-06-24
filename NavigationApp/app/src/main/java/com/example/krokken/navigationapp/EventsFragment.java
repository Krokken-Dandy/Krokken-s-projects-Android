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

public class EventsFragment extends Fragment {

    private int eventsPrimaryColorID = R.color.primary_event_color;
    private int eventsPopupColorID = R.color.popup_event_color;
    private ArrayList<List> eventsList;
    private PopupWindow popupWindow;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources res = getResources();

        //All of the images used as icons for the events
        int[] eventsImages = {R.drawable.ic_rabblefish_event, R.drawable.ic_larry_lacerte_event,
                R.drawable.ic_greg_brown_event, R.drawable.ic_taylor_shae_event,
                R.drawable.ic_bandshell_boogie_event};

        //The names of all the events
        String[] eventsNames = res.getStringArray(R.array.events_names);

        //Addresses for all the events
        String[] eventsAddresses = res.getStringArray(R.array.events_addresses);

        //Operating hours for each event
        String[] eventsOperatingHours = res.getStringArray(R.array.events_hours);

        //Phone numbers for each event
        String[] eventsPhoneNumbers = res.getStringArray(R.array.events_phone_numbers);

        //Websites for each event
        String[] eventsWebsites = res.getStringArray(R.array.events_websites);

        //Descriptions of the events
        String[] eventsDescriptions = res.getStringArray(R.array.events_descriptions);

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        eventsList = new ArrayList<List>();

        for (int i = 0; i < eventsImages.length; i++) {
            eventsList.add(new List(eventsNames[i], eventsImages[i], eventsAddresses[i],
                    eventsOperatingHours[i], eventsPhoneNumbers[i], eventsWebsites[i],
                    eventsDescriptions[i]));
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(), eventsList, eventsPrimaryColorID);
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

        final List list = eventsList.get(position);

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
        popupFrame.setBackgroundColor(getResources().getColor(eventsPopupColorID));

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