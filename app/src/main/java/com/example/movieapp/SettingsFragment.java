package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import android.content.SharedPreferences;
import android.os.Bundle;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_settings, rootKey);
      //  SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
     //   PreferenceScreen preferenceScreen = getPreferenceScreen();

    }
}
