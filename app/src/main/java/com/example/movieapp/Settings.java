package com.example.movieapp;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class Settings extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, new SettingsFragment())
                .commit();


    }
}
