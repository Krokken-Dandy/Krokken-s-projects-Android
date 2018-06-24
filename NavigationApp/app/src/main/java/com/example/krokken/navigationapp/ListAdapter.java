package com.example.krokken.navigationapp;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List> {

    private int colorResourceId;

    public ListAdapter(Activity context, ArrayList<List> word, int color) {
        super(context, 0, word);
        colorResourceId = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        List currentWord = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.location_name);
        nameTextView.setText(currentWord.getLocationName());

        ImageView iconView = listItemView.findViewById(R.id.list_item_icon);

        if (currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getLocationIcon());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }

        // Return the whole list item layout
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
