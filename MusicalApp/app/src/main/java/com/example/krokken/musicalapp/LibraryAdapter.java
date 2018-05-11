package com.example.krokken.musicalapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryAdapter extends ArrayAdapter<Library> {

    private Context mContext;


    public LibraryAdapter(Activity context, ArrayList<Library> list) {
        super(context, 0, list);
        mContext = context;
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
            viewHolder.image = row.findViewById(R.id.image);
            viewHolder.imageTitle = row.findViewById(R.id.text);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
//        viewHolder.image.setImageBitmap(libraryClass.getImage());
        viewHolder.image.setImageResource(R.drawable.sample_0);
        viewHolder.imageTitle.setText("Taco");
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}

