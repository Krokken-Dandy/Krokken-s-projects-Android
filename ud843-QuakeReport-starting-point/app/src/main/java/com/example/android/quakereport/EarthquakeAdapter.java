package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        Earthquake eq = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.locationPart1 = (TextView) convertView.findViewById(R.id.location_part_1);
            viewHolder.locationPart2 = (TextView) convertView.findViewById(R.id.location_part_2);
            viewHolder.magnitudeView = (TextView) convertView.findViewById(R.id.magnitude);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Date dateObject = new Date(eq.getTime());

        viewHolder.locationPart1.setText(eq.getLocationPart1());
        viewHolder.locationPart2.setText(eq.getLocationPart2());
        viewHolder.magnitudeView.setText("" + eq.getMagnitude());
        viewHolder.date.setText(getFormattedDate(dateObject));
        viewHolder.time.setText(getFormattedTime(dateObject));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) viewHolder.magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(eq.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        return convertView;
    }

    static class ViewHolder {
        private TextView locationPart1;
        private TextView locationPart2;
        private TextView magnitudeView;
        private TextView date;
        private TextView time;
    }

    private String getFormattedDate (Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String getFormattedTime (Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColor;
        int mag = (int)magnitude;
        switch (mag) {
            case 0:
            case 1:
                magnitudeColor = R.color.magnitude1;
                break;
            case 2:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            default:
                magnitudeColor = R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(),magnitudeColor);
    }
}
