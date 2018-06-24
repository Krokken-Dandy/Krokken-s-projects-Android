package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> word, int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, word);
        mColorResourceId = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        nameTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current Word object and
        // set this text on the number TextView
        numberTextView.setText(currentWord.getmDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);

        if (currentWord.hasImage()) {
            // Get the image resource ID from the current Word object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }

}
