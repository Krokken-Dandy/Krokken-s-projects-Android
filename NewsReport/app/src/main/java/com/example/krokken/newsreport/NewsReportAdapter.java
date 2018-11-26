package com.example.krokken.newsreport;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.example.krokken.newsreport.NewsReportActivity.LOG_TAG;

public class NewsReportAdapter extends ArrayAdapter<NewsReport> {

    ViewHolder viewHolder;

    public NewsReportAdapter(Activity context, ArrayList<NewsReport> newsReports) {
        super(context, 0, newsReports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsReport nr = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.news_list_item, parent, false);

            // ViewHolder for all the views in the ListView
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.section = (TextView) convertView.findViewById(R.id.section);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Log.v("new title", "" + nr.getNewsTitle());

        // Sets the Title of the article
        viewHolder.title.setText(nr.getNewsTitle());

        // Sets the Date the article was posted
        viewHolder.date.setText(getFormattedDate(nr));

        // Sets the Time the article was posted
        viewHolder.time.setText(getFormattedTime(nr));

        // Sets the Section Name of the article
        viewHolder.section.setText(nr.getSectionName());

        // Sets the name of the Contributor to the article
        viewHolder.author.setText(nr.getContributorName());

        return convertView;
    }

    static class ViewHolder {
        private TextView title;
        private TextView date;
        private TextView time;
        private TextView section;
        private TextView author;
    }

    // Formats and gets the date the article was posted
    private String getFormattedDate(NewsReport nr) {
        SimpleDateFormat dateFromJson = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        String date = "";

        try {
            Date dateObject = dateFromJson.parse(nr.getTime());
            Locale currentLocale = getContext().getResources().getConfiguration().locale;
            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, currentLocale);
            date = df.format(dateObject);
        } catch (ParseException e) {
            Log.v(LOG_TAG, "Issue parsing date", e);
        }
        return date;
    }

    // Formats and gets the time the article was posted
    private String getFormattedTime(NewsReport nr) {
        SimpleDateFormat dateFromJson = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        String time = "";

        try {
            Date dateObject = dateFromJson.parse(nr.getTime());
            Locale currentLocale = getContext().getResources().getConfiguration().locale;
            DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT, currentLocale);
            time = df.format(dateObject);
        } catch (ParseException e) {
            Log.v(LOG_TAG, "Issue parsing time", e);
        }
        return time;
    }
}
