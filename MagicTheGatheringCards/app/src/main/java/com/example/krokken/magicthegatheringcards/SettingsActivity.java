package com.example.krokken.magicthegatheringcards;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class CardFilterPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference chosenName = findPreference(getString(R.string.settings_chosen_search_by_name_key));
            bindPreferenceSummaryToValue(chosenName);

            Preference chooseCMCLess = findPreference(getString(R.string.settings_choose_cmc_less_key));
            bindPreferenceSummaryToValue(chooseCMCLess);

            Preference chooseCMCGreater = findPreference(getString(R.string.settings_choose_cmc_greater_key));
            bindPreferenceSummaryToValue(chooseCMCGreater);

            Preference chooseColors = findPreference(getString(R.string.settings_choose_colors_key));
            bindPreferenceSummaryToValue(chooseColors);

            Preference chooseTypes = findPreference(getString(R.string.settings_choose_types_key));
            bindPreferenceSummaryToValue(chooseTypes);

            Preference chooseSubtypes = findPreference(getString(R.string.settings_choose_subtypes_key));
            bindPreferenceSummaryToValue(chooseSubtypes);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            // The code in this method takes care of updating the displayed preference summary after it has been changed
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}
