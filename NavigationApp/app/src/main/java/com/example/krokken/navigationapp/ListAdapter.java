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
        final ViewHolder item = new ViewHolder();
        item.nameTextView = listItemView.findViewById(R.id.location_name);
        item.nameTextView.setText(currentWord.getLocationName());

        item.iconView = listItemView.findViewById(R.id.list_item_icon);

        if (currentWord.hasImage()) {
            item.iconView.setImageResource(currentWord.getLocationIcon());
            item.iconView.setVisibility(View.VISIBLE);
        } else {
            item.iconView.setVisibility(View.GONE);
        }

        // Return the whole list item layout
        item.textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        item.textContainer.setBackgroundColor(color);

        return listItemView;
    }

    static class ViewHolder {
        private TextView nameTextView;
        private ImageView iconView;
        private View textContainer;
    }
}
