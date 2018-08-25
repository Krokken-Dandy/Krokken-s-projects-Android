package com.example.krokken.newsreport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.krokken.newsreport.NewsReportActivity.LOG_TAG;

public final class QueryUtils {

    // Each used to retrieve related object in the JSON response
    private static final String WEB_TITLE = "webTitle";
    private static final String WEB_PULICATION_DATE = "webPublicationDate";
    private static final String WEB_URL = "webUrl";
    private static final String WEB_SECTION_NAME = "sectionName";
    private static final String WEB_RESPONSE = "response";
    private static final String WEB_RESULTS = "results";
    private static final String WEB_TAGS ="tags";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link NewsReport} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<NewsReport> extractFeatureFromJson(String newsReportJson) {

        if (TextUtils.isEmpty(newsReportJson)) return null;

        // Create an empty ArrayList that we can start adding news articles to
        List<NewsReport> newsReports = new ArrayList<>();

        // Tries to parse the JSON response
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Build up a list of News objects with the corresponding data.
            // Base JSON response
            JSONObject baseJsonResponse = new JSONObject(newsReportJson);

            // First object needed to get most of the info
            JSONObject devResponse = baseJsonResponse.getJSONObject(WEB_RESPONSE);

            // The array that will hold the info of each article
            JSONArray newsReportArray = devResponse.getJSONArray(WEB_RESULTS);

            // Loop to build the List with all the info of the articles
            for (int i = 0; i < newsReportArray.length(); i++) {
                // JSON object for each item in the JSON array
                JSONObject currentNewsReport = newsReportArray.getJSONObject(i);

                // Gets the title of the article, so long as one is provided
                String title = "";
                if (currentNewsReport.has(WEB_TITLE)) {
                    title = currentNewsReport.getString(WEB_TITLE);
                }

                // Gets the title of the article, so long as one is provided
                String date = "";
                if (currentNewsReport.has(WEB_PULICATION_DATE)) {
                    date = currentNewsReport.getString(WEB_PULICATION_DATE);
                }

                // Gets the website of the article, so long as one is provided
                // Used to view the full article, used by the intent if clicked in ListView
                String website = "";
                if (currentNewsReport.has(WEB_URL)) {
                    website = currentNewsReport.getString(WEB_URL);
                }

                // Gets the section of the article, so long as one is provided
                String sectionName = "";
                if (currentNewsReport.has(WEB_SECTION_NAME)) {
                    sectionName = currentNewsReport.getString(WEB_SECTION_NAME);
                }

                String contributorName = "The Guardian News";
                if (currentNewsReport.has(WEB_TAGS)) {
                    // Gets the array with additional information for the article
                    // WEB_TITLE in this section contains the contributor's name
                    JSONArray newsTagsArray = currentNewsReport.getJSONArray(WEB_TAGS);

                    for (int j = 0; j < newsTagsArray.length(); j++) {
                        JSONObject currentTag = newsTagsArray.getJSONObject(j);
                        if (currentNewsReport.has(WEB_TITLE)) {
                            contributorName = currentTag.getString(WEB_TITLE);
                        }
                    }
                }

                NewsReport newsReport = new NewsReport(title, website, date, sectionName, contributorName);
                newsReports.add(newsReport);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        // Return the list of news articles
        return newsReports;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("QueryUtils", "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(25000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "The response code was not 200, it was: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("IOException", "IOEception in makeHttpRequest", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Query the Guardian dataset and return a list of {@link NewsReport} objects.
     */
    public static List<NewsReport> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link NewsReport}s
        List<NewsReport> newsReports = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link NewsReport}s
        return newsReports;
    }
}