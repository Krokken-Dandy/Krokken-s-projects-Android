package com.example.krokken.musicalapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryAdapter extends ArrayAdapter<Library> {

    private Context mContext;
    private int mBackgroundColorResource;


    public LibraryAdapter(Activity context, ArrayList<Library> list, int color) {
        super(context, 0, list);
        mContext = context;
        mBackgroundColorResource = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder viewHolder;
        final Library library = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.library_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.albumImage = row.findViewById(R.id.image);
            viewHolder.songName = row.findViewById(R.id.text);
            viewHolder.songDuration = row.findViewById(R.id.song_duration);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
        View linearLayout = row.findViewById(R.id.text_container);
        int backgroundColor = ContextCompat.getColor(getContext(), mBackgroundColorResource);
        linearLayout.setBackgroundColor(backgroundColor);
        viewHolder.albumImage.setImageBitmap(library.getImage());
        viewHolder.songName.setText(library.getTitle());
        viewHolder.songDuration.setText(library.getDuration());
        return row;
    }

    static class ViewHolder {
        TextView songName;
        ImageView albumImage;
        TextView songDuration;
    }
}

