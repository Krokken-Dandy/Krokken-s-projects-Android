package com.example.krokken.magicthegatheringcards;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** Imports for Jackson JSON */
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.krokken.magicthegatheringcards.CardsActivity.LOG_TAG;

public final class QueryUtils {

    // Each used to retrieve related object in the JSON response
    private static final String CARD_LAYOUT = "layout";
    private static final String CARD_NAME = "name";
    private static final String CARD_MANA_COST = "manaCost";
    private static final String CARD_CMC = "convertedManaCost";
    private static final String CARD_TYPE = "type";
    private static final String CARD_TYPES = "types";
    private static final String CARD_SUBTYPES = "subtypes";
    private static final String CARD_TEXT = "text";
    private static final String CARD_POWER = "power";
    private static final String CARD_TOUGHNESS = "toughness";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Cards} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<Cards> extractFeatureFromJson(String cardsJson) {

        if (TextUtils.isEmpty(cardsJson)) return null;
        /** Tried with GSON and wasn't working */
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        try {
//            Gson gson
//            CardPOJO cardPOJO = objectMapper.readValue(cardsJson, CardPOJO.class);
//        } catch (IOException e) {
//
//        }

        // Create an empty ArrayList that we can start adding news articles to
        List<Cards> cards = new ArrayList<>();

        /** Trying Jackson JSON parsing, wasn't working */
//        try {
//
//            JsonFactory jsonFactory = new JsonFactory();
//            JsonParser jsonParser = jsonFactory.createParser(new File(cardsJson));
//
//            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
//                String fieldName = jsonParser.getCurrentName();
//                String cardLayoutString = "";
//                String cardNameString = "";
//                String cardManaCostString = "";
//                String cardCMCString = "";
//                String cardTypeString = "";
//                String[] cardTypesStringArray;
//                if (fieldName.equals(CARD_TYPES)) {
//                    jsonParser.nextToken();
//                    int length = fieldName.length();
//                    cardTypesStringArray = new String[length];
//                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
//                        int i = 0;
//                        cardTypesStringArray[i] = jsonParser.getText();
//                        i++;
//                    }
//                } else {
//                    cardTypesStringArray = new String[0];
//                }
//
//                String[] cardSubtypesStringArray;
//                if (fieldName.equals(CARD_SUBTYPES)) {
//                    int length = fieldName.length();
//                    cardSubtypesStringArray = new String[length];
//                    int i = 0;
//                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
//                        cardSubtypesStringArray[i] = jsonParser.getText();
//                        i++;
//                    }
//                } else {
//                    cardSubtypesStringArray = new String[0];
//                }
//
//                String cardTextString = "";
//                String cardPowerString = "";
//                String cardToughnessString = "";
//
//                switch (fieldName) {
//                    case CARD_LAYOUT:
//                        jsonParser.nextToken();
//                        cardLayoutString = jsonParser.getText();
//                        break;
//                    case CARD_NAME:
//                        jsonParser.nextToken();
//                        cardNameString = jsonParser.getText();
//                        break;
//                    case CARD_MANA_COST:
//                        jsonParser.nextToken();
//                        cardManaCostString = jsonParser.getText();
//                        break;
//                    case CARD_CMC:
//                        jsonParser.nextToken();
//                        cardCMCString = jsonParser.getText();
//                        break;
//                    case CARD_TYPE:
//                        jsonParser.nextToken();
//                        cardTypeString = jsonParser.getText();
//                        break;
//                    case CARD_TEXT:
//                        jsonParser.nextToken();
//                        cardTextString = jsonParser.getText();
//                        break;
//                    case CARD_POWER:
//                        jsonParser.nextToken();
//                        cardPowerString = jsonParser.getText();
//                        break;
//                    case CARD_TOUGHNESS:
//                        jsonParser.nextToken();
//                        cardToughnessString = jsonParser.getText();
//                        break;
//                    default:
//                        break;
//                }
//               Log.v("Log", "" + cardLayoutString + cardNameString + cardManaCostString +
//                        cardCMCString + cardTypeString + cardTypesStringArray + cardSubtypesStringArray +
//                        cardTextString + cardPowerString + cardToughnessString);
//
//                Cards cardInfo = new Cards(cardLayoutString, cardNameString, cardManaCostString,
//                            cardCMCString, cardTypeString, cardTypesStringArray, cardSubtypesStringArray,
//                            cardTextString, cardPowerString, cardToughnessString);
//
//                cards.add(cardInfo);
//            }

        /**
         * Normal JSON parsing with Android
         * */
        try {
            JSONObject baseJsonResponse = new JSONObject(cardsJson);

            Iterator<String> iter = baseJsonResponse.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    JSONObject devResponse = baseJsonResponse.getJSONObject(key);

                    String cardLayoutString = "";
                    if (devResponse.has(CARD_LAYOUT)) {
                        cardLayoutString = devResponse.getString(CARD_LAYOUT);
                    }

                    String cardNameString = "";
                    if (devResponse.has(CARD_NAME)) {
                        cardNameString = devResponse.getString(CARD_NAME);
                    }

                    String cardManaCostString = "";
                    if (devResponse.has(CARD_MANA_COST)) {
                        cardManaCostString = devResponse.getString(CARD_MANA_COST);
                    }

                    String cardCMCString = "";
                    if (devResponse.has(CARD_CMC)) {
                        cardCMCString = devResponse.getString(CARD_CMC);
                    }

                    String cardTypeString = "";
                    if (devResponse.has(CARD_TYPE)) {
                        cardTypeString = devResponse.getString(CARD_TYPE);
                    }

                    JSONArray typesArray = devResponse.getJSONArray(CARD_TYPES);
                    int typesArrayLength = typesArray.length();
                    String[] cardTypesStringArray = new String[typesArrayLength];
                    if (typesArrayLength > 0) {
                        for (int i = 0; i < typesArrayLength; i++) {
                            cardTypesStringArray[i] = typesArray.getString(i);
                        }
                    }

                    JSONArray subtypesArray = devResponse.getJSONArray(CARD_SUBTYPES);
                    int subtypesArrayLength = subtypesArray.length();
                    String[] cardSubtypesStringArray = new String[subtypesArrayLength];
                    if (subtypesArrayLength > 0) {
                        for (int i = 0; i < subtypesArrayLength; i++) {
                            cardSubtypesStringArray[i] = subtypesArray.getString(i);
                        }
                    }

                    String cardTextString = "";
                    if (devResponse.has(CARD_TEXT)) {
                        cardTextString = devResponse.getString(CARD_TEXT);
                    }

                    String cardPowerString = "";
                    if (devResponse.has(CARD_POWER)) {
                        cardPowerString = devResponse.getString(CARD_POWER);
                    }

                    String cardToughnessString = "";
                    if (devResponse.has(CARD_TOUGHNESS)) {
                        cardToughnessString = devResponse.getString(CARD_TOUGHNESS);
                    }

                    Cards cardInfo = new Cards(cardLayoutString, cardNameString, cardManaCostString,
                            cardCMCString, cardTypeString, cardTypesStringArray, cardSubtypesStringArray,
                            cardTextString, cardPowerString, cardToughnessString);

                    cards.add(cardInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }
        // For JSON Jackson
        /* catch (IOException e){
            e.printStackTrace();
        }*/

        // Return the list of cards
        return cards;
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
        Log.v("HttpRequest returned", ""+jsonResponse);
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
        Log.v("ReadFromStream", " commenced");
        return output.toString();
    }

    /**
     * Query the Guardian dataset and return a list of {@link Cards} objects.
     */
    public static List<Cards> fetchCardData(String requestUrl, Context context) {
        String jsonResponse = null;
        // Create URL object
        URL url = createUrl(requestUrl);
        File file = new File(context.getFilesDir(), "AllCards.json");

        if (file.exists()) {
            jsonResponse = loadJSONFromAsset(context);
        } else {
            // Perform HTTP request to the URL and receive a JSON response back
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e("QueryUtils", "Problem making the HTTP request.", e);
            }
        }

        // Extract relevant fields from the JSON response and create a list of {@link Cards}
        List<Cards> cardsList = extractFeatureFromJson(jsonResponse);

        Log.v("FetchCardData returned", "Cards list");
        return cardsList;
    }

    private static String loadJSONFromAsset(Context context) {
        StringBuilder output = new StringBuilder();

        File file = new File(context.getFilesDir(), "AllCards.json");

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));

            String line = fileReader.readLine();
            while (line != null) {
                output.append(line);
                line = fileReader.readLine();
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        Log.v("LoadJSONFromAsset", " finishing");
        return output.toString();
    }

    public static Boolean checkJSONVersion(String requestUrl, Context context) {
        String[] jsonResponse = new String[2];

        // Create URL object
        URL url = createUrl(requestUrl);

        try {
            jsonResponse[0] = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem making the HTTP request.", e);
        }

        if (TextUtils.isEmpty(jsonResponse[0])) return false;

        File file = new File(context.getFilesDir(), "version.json");

        if (file.exists()) {
            try {
                FileInputStream fileInputStream = context.openFileInput("version.json");

                int size = fileInputStream.available();

                byte[] buffer = new byte[size];

                fileInputStream.read(buffer);

                fileInputStream.close();

                jsonResponse[1] = new String(buffer, "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        String[] versionString = new String[2];
        JSONObject baseJsonResponse = null;
        try {
            for (int i = 0; i < jsonResponse.length; i++) {
                baseJsonResponse = new JSONObject(jsonResponse[i]);

                if (baseJsonResponse.has("version")) {
                    versionString[i] = baseJsonResponse.getString("version");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("Version web", versionString[0]);
        Log.v("Version file", versionString[1]);
        Log.v("Version file", "" + (versionString[0].equals(versionString[1])));
        return (versionString[0].equals(versionString[1]));
    }
}