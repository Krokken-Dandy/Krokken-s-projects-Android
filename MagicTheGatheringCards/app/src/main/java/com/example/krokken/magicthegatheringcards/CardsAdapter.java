package com.example.krokken.magicthegatheringcards;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CardsAdapter extends ArrayAdapter<Cards> implements Filterable {

    ViewHolder viewHolder;
    Context mContext;
    Resources res;
    SharedPreferences sharedPreferences;
    ArrayList<Cards> mCardsOriginalData;
    ArrayList<Cards> mCardsFilteredData;
    String[] globalSearchTerms = {"", "any", "all", "everything"};
    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if (mCardsOriginalData != null && charSequence != null) {
                ArrayList<Cards> nList = new ArrayList<>();
                int listSize = mCardsOriginalData.size();
                for (int i = 0; i < listSize; i++) {
                    Cards item = mCardsOriginalData.get(i);
                    if (filterThroughAllOptions(item) && checkSearchView(item, charSequence)) {
                        nList.add(item);
                    }
                }
                filterResults.count = nList.size();
                filterResults.values = nList;
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mCardsFilteredData = (ArrayList<Cards>) filterResults.values;
            if (filterResults.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    };

    public CardsAdapter(Activity context, ArrayList<Cards> cards) {
        super(context, 0, cards);
        mContext = context;
        res = mContext.getResources();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        this.mCardsOriginalData = cards;
        this.mCardsFilteredData = cards;
    }

    @Override
    public int getCount() {
        return mCardsFilteredData.size();
    }

    @Override
    public Cards getItem(int position) {
        return mCardsFilteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cards cardsPosition = mCardsFilteredData.get(position);

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

        viewHolder.type.setText(cardsPosition.getCardType());

        if (!cardsPosition.getCardPower().equals("") || !cardsPosition.getCardToughness().equals("")) {
            viewHolder.powerAndToughness.setText(cardsPosition.getCardPNT());
        } else {
            viewHolder.powerAndToughness.setText("");
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private boolean filterThroughAllOptions(Cards cardsPosition) {
        //TODO Add power/toughness filter, text filter

        return (filterCMCOptions(cardsPosition) &&
                filterNameOptions(cardsPosition) &&
                filterColorCostOptions(cardsPosition) &&
                filterTypeOptions(cardsPosition) &&
                filterSubtypeOptions(cardsPosition));
    }

    // Returns true if CMC is null or card equals chosen cmc in search
    private boolean filterCMCOptions(Cards cardsPosition) {
        double chosenCMCLessThan = getChosenCMCLessThan();
        double chosenCMCGreaterThan = getChosenCMCGreaterThan();
        if (cardsPosition.getCardCMC() == null) return true;

        return ((Double.parseDouble(cardsPosition.getCardCMC()) <= chosenCMCLessThan &&
                (Double.parseDouble(cardsPosition.getCardCMC()) >= chosenCMCGreaterThan)));
    }

    // Returns true if Name is null or card equals chosen name in search
    private boolean filterNameOptions(Cards cardsPosition) {
        String[] chosenNameArray = getChosenNameString();
        if (cardsPosition.getCardName() == null || checkGlobalSearchTerms(chosenNameArray))
            return true;
        for (String chosenNames : chosenNameArray) {
            if (Pattern.compile(Pattern.quote(cardsPosition.getCardName()),
                    Pattern.CASE_INSENSITIVE).matcher(chosenNames).find()) {
                return true;
            }
        }
        return false;
    }

    // Returns true if color is null or card equals chosen colors in search
    private boolean filterColorCostOptions(Cards cardsPosition) {
        String[] chosenColorsArray = getChosenColors();
        String[] alteredChosenColorsArray = new String[chosenColorsArray.length];
        if (cardsPosition.getCardManaCost() == null || checkGlobalSearchTerms(chosenColorsArray)) {
            return true;
        }
        String[] cardManaCostArray = cardsPosition.getCardManaCost();

        for (int i = 0; i < chosenColorsArray.length; i++) {
            switch (chosenColorsArray[i]) {
                case "blue":
                    alteredChosenColorsArray[i] = chosenColorsArray[i].replaceAll("blue", "U");
                    break;
                case "black":
                    alteredChosenColorsArray[i] = chosenColorsArray[i].replaceAll("black", "B");
                    break;
                case "white":
                    alteredChosenColorsArray[i] = chosenColorsArray[i].replaceAll("white", "W");
                    break;
                case "red":
                    alteredChosenColorsArray[i] = chosenColorsArray[i].replaceAll("red", "R");
                    break;
                case "green":
                    alteredChosenColorsArray[i] = chosenColorsArray[i].replaceAll("green", "G");
                    break;
                default:
                    alteredChosenColorsArray[i] = chosenColorsArray[i];
            }
        }

        for (String cardManaCost : cardManaCostArray) {
            for (String chosenColors : alteredChosenColorsArray) {
                if (Pattern.compile(Pattern.quote(cardManaCost),
                        Pattern.CASE_INSENSITIVE).matcher(chosenColors).find()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Returns true if type is null or card equals chosen types in search
    private boolean filterTypeOptions(Cards cardsPosition) {
        String[] chosenTypesArray = getChosenTypes();
        if (cardsPosition.getCardTypes() == null || checkGlobalSearchTerms(chosenTypesArray)) {
            return true;
        }
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

    // Returns true if subtypes is null or card equals chosen subtypes in search
    private boolean filterSubtypeOptions(Cards cardsPosition) {
        String[] chosenSubtypesArray = getChosenSubtypes();
        if (cardsPosition.getCardSubtypes() == null || checkGlobalSearchTerms(chosenSubtypesArray)) {
            return true;
        }
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

    private boolean checkSearchView(Cards cardsPosition, CharSequence charSequence) {
        if (cardsPosition.getCardName() == null || checkGlobalSearchTerms(splitNamesString(charSequence.toString())))
            return true;
        for (String chosenNames : splitNamesString(charSequence.toString())) {
            if (Pattern.compile(Pattern.quote(cardsPosition.getCardName()),
                    Pattern.CASE_INSENSITIVE).matcher(chosenNames).find()) {
                return true;
            }
        }
        return false;
    }

    // Returns true if search term is one of the globals: "", "any", "all", "everything"
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

    private String[] getChosenNameString() {
        String chosenNameString = sharedPreferences.getString(
                res.getString(R.string.settings_chosen_search_by_name_key),
                res.getString(R.string.settings_chosen_search_by_name_default));
        String[] chosenNames = splitNamesString(chosenNameString);
        return chosenNames;
    }

    private String[] getChosenColors() {
        String chosenColorsString = sharedPreferences.getString(
                res.getString(R.string.settings_choose_colors_key),
                res.getString(R.string.settings_choose_colors_default));
        String[] chosenColors = splitColorsString(chosenColorsString);
        return chosenColors;
    }

    private String[] getChosenTypes() {
        String chosenTypesString = sharedPreferences.getString(
                res.getString(R.string.settings_choose_types_key),
                res.getString(R.string.settings_choose_types_default));
        String[] chosenTypes = splitTypesString(chosenTypesString);
        return chosenTypes;
    }

    private String[] getChosenSubtypes() {
        String chosenSubtypesString = sharedPreferences.getString(
                res.getString(R.string.settings_choose_subtypes_key),
                res.getString(R.string.settings_choose_subtypes_default));
        String[] chosenSubtypes = splitSubtypesString(chosenSubtypesString);
        return chosenSubtypes;
    }

    private double getChosenCMCLessThan() {
        double chosenCMCLessThan = Double.parseDouble(sharedPreferences.getString(
                res.getString(R.string.settings_choose_cmc_less_key),
                res.getString(R.string.settings_choose_cmc_less_default))
        );
        return chosenCMCLessThan;
    }

    private double getChosenCMCGreaterThan() {
        double chosenCMCGreaterThan = Double.parseDouble(sharedPreferences.getString(
                res.getString(R.string.settings_choose_cmc_greater_key),
                res.getString(R.string.settings_choose_cmc_greater_default))
        );
        return chosenCMCGreaterThan;
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
