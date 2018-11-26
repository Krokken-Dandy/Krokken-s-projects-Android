package com.example.krokken.magicthegatheringcards;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CardsAdapter extends ArrayAdapter<Cards> {

    ViewHolder viewHolder;
    Context mContext;
    ArrayList<Cards> mCards;
    String[] globalSearchTerms = {"", "any", "all", "everything"};

    public CardsAdapter(Activity context, ArrayList<Cards> cards) {
        super(context, 0, cards);
        mContext = context;
        mCards = cards;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cards cardsPosition = getItem(position);
        CardsActivity cardsActivity = new CardsActivity();
        Resources res = mContext.getResources();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
// getString retrieves a String value from the preferences. The second parameter is the default value for this preference.
        String chosenNameString = sharedPreferences.getString(
                res.getString(R.string.settings_chosen_search_by_name_key),
                res.getString(R.string.settings_chosen_search_by_name_default));

        String[] chosenNames = splitNamesString(chosenNameString);

        String chosenColorsString = sharedPreferences.getString(
                res.getString(R.string.settings_choose_colors_key),
                res.getString(R.string.settings_choose_colors_default));

        String[] chosenColors = splitColorsString(chosenColorsString);

        String chosenTypesString = sharedPreferences.getString(
                res.getString(R.string.settings_choose_types_key),
                res.getString(R.string.settings_choose_types_default));

        String[] chosenTypes = splitTypesString(chosenTypesString);

        String chosenSubtypesString = sharedPreferences.getString(
                res.getString(R.string.settings_choose_subtypes_key),
                res.getString(R.string.settings_choose_subtypes_default));

        String[] chosenSubtypes = splitSubtypesString(chosenSubtypesString);

        double chosenCMCLessThan = Double.parseDouble(sharedPreferences.getString(
                res.getString(R.string.settings_choose_cmc_less_key),
                res.getString(R.string.settings_choose_cmc_less_default))
        );

        double chosenCMCGreaterThan = Double.parseDouble(sharedPreferences.getString(
                res.getString(R.string.settings_choose_cmc_greater_key),
                res.getString(R.string.settings_choose_cmc_greater_default))
        );

        if (!filterThroughAllOptions(cardsPosition, chosenCMCLessThan, chosenCMCGreaterThan,
                chosenNames, chosenColors, chosenTypes, chosenSubtypes)) {
            return convertView;
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.cards_list_item, parent, false);

            // ViewHolder for all the views in the ListView
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.card_name);
            viewHolder.cmc = convertView.findViewById(R.id.card_cmc);
            viewHolder.type = convertView.findViewById(R.id.card_type);
            viewHolder.powerAndToughness = convertView.findViewById(R.id.card_power_and_toughness);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(cardsPosition.getCardName().replaceAll("[\"]", ""));

        viewHolder.cmc.setText(cardsPosition.getCardCMC());

        if (!cardsPosition.getCardPower().equals("") || !cardsPosition.getCardToughness().equals("")) {
            viewHolder.powerAndToughness.setText(cardsPosition.getCardPNT());
        }

        // Sets the name of the Contributor to the article
        viewHolder.type.setText(cardsPosition.getCardType());

        return convertView;
    }

    private boolean filterThroughAllOptions(
            Cards cardsPosition,
            double chosenCMCLessThan, double chosenCMCGreaterThan,
            String[] chosenName,
            String[] chosenColors,
            String[] chosenTypes,
            String[] chosenSubtypes) {

        //TODO Add power/toughness filter, text filter
        return (filterCMCOptions(cardsPosition, chosenCMCLessThan, chosenCMCGreaterThan) &&
                filterNameOptions(cardsPosition, chosenName) &&
                filterColorCostOptions(cardsPosition, chosenColors) &&
                filterTypeOptions(cardsPosition, chosenTypes) &&
                filterSubtypeOptions(cardsPosition, chosenSubtypes));
    }

    private boolean filterCMCOptions(Cards cardsPosition,
                                     double chosenCMCLessThan, double chosenCMCGreaterThan) {

        if (cardsPosition.getCardCMC() == null) return true;

        return ((Double.parseDouble(cardsPosition.getCardCMC()) <= chosenCMCLessThan &&
                (Double.parseDouble(cardsPosition.getCardCMC()) >= chosenCMCGreaterThan)));
    }

    private boolean filterNameOptions(Cards cardsPosition, String[] chosenNameArray) {
        if (cardsPosition.getCardName() == null || checkGlobalSearchTerms(chosenNameArray)) return true;
        for (String chosenNames : chosenNameArray) {
            if (Pattern.compile(Pattern.quote(cardsPosition.getCardName()),
                    Pattern.CASE_INSENSITIVE).matcher(chosenNames).find()) {
                return true;
            }
        }
        return false;
    }

    private boolean filterColorCostOptions(Cards cardsPosition, String[] chosenColorsArray) {
        if (cardsPosition.getCardManaCost() == null || checkGlobalSearchTerms(chosenColorsArray)) {
            return true;
        }
        String[] cardManaCostArray = cardsPosition.getCardManaCost();
        for (String cardManaCosts : cardManaCostArray) {
            for (String chosenColors : chosenColorsArray) {
                if (Pattern.compile(Pattern.quote(cardManaCosts),
                        Pattern.CASE_INSENSITIVE).matcher(chosenColors).find()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean filterTypeOptions(Cards cardsPosition, String[] chosenTypesArray) {
        if (cardsPosition.getCardTypes() == null || checkGlobalSearchTerms(chosenTypesArray))
            return true;
        String[] cardTypesArray = cardsPosition.getCardTypes();
        for (String cardTypes : cardTypesArray) {
            for (String chosenTypes : chosenTypesArray) {
                if (Pattern.compile(Pattern.quote(cardTypes),
                        Pattern.CASE_INSENSITIVE).matcher(chosenTypes).find()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean filterSubtypeOptions(Cards cardsPosition, String[] chosenSubtypesArray) {
        if (cardsPosition.getCardSubtypes() == null || checkGlobalSearchTerms(chosenSubtypesArray))
            return true;
        String[] cardSubtypesArray = cardsPosition.getCardSubtypes();
        for (String cardSubtypes : cardSubtypesArray) {
            for (String chosenSubtypes : chosenSubtypesArray) {
                if (Pattern.compile(Pattern.quote(cardSubtypes),
                        Pattern.CASE_INSENSITIVE).matcher(chosenSubtypes).find()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkGlobalSearchTerms(String[] globalTerm) {
        for (String enteredTerms : globalTerm) {
            for (String terms : globalSearchTerms) {
                if (enteredTerms.toLowerCase().trim().equals(terms)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String[] splitNamesString(String chosenNamesString) {
        chosenNamesString = chosenNamesString.toLowerCase().replaceAll(" ", "");
        String[] splitNames = chosenNamesString.split("[,]");
        return splitNames;
    }

    private String[] splitColorsString(String chosenColorsString) {
        chosenColorsString = chosenColorsString.toLowerCase().replaceAll(" ", "");
        String[] splitColors = chosenColorsString.split("[,]");
        return splitColors;
    }

    private String[] splitTypesString(String chosenTypesString) {
        chosenTypesString = chosenTypesString.toLowerCase().replaceAll(" ", "");
        String[] splitTypes = chosenTypesString.split("[,]");
        return splitTypes;
    }

    private String[] splitSubtypesString(String chosenSubtypesString) {
        chosenSubtypesString = chosenSubtypesString.toLowerCase().replaceAll(" ", "");
        String[] splitSubtypes = chosenSubtypesString.split("[,]");
        return splitSubtypes;
    }

    static class ViewHolder {
        private TextView name;
        private TextView type;
        private TextView cmc;
        private TextView powerAndToughness;
    }
}
