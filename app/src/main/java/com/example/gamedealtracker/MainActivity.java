package com.example.gamedealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private View dealTypePC;
    private View dealTypePS;
    private View dealTypeXB;
    private View dealTypeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize interactive deal types
        dealTypePC = findViewById(R.id.dealTypePC);
        dealTypePS = findViewById(R.id.dealTypePS);
        dealTypeXB = findViewById(R.id.dealTypeXB);
        dealTypeAll = findViewById(R.id.dealTypeAll);

        // Call method to set up UI
        setUpUi();
    }

    // Sets up interactive deal types with UI content
    private void setUpUi() {
        // Set up PC deal type
        setSingleTypeUi(dealTypePC, getDrawable(R.drawable.ic_baseline_computer_24),
                "Browse deals for PC");

        // Set up PlayStation deal type
        setSingleTypeUi(dealTypePS, getDrawable(R.drawable.ic_playstation_logo),
                "Browse deals for PlayStation");

        // Set up XBox deal type
        setSingleTypeUi(dealTypeXB, getDrawable(R.drawable.ic_xbox_logo),
                "Browse deals for XBox");

        // Set up Misc deal type
        setSingleTypeUi(dealTypeAll, getDrawable(R.drawable.ic_baseline_money_24),
                "Browse all deals");
    }

    // Set up UI for a single deal type
    private void setSingleTypeUi(View dealType, Drawable icon, String msg) {
        // Set up visual aspects
        ((ImageView)dealType.findViewById(R.id.ivDealType)).setImageDrawable(icon);
        ((TextView)dealType.findViewById(R.id.tvDealType)).setText(msg);

        // Set up click listener
        dealType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Clicked " +
                        dealType.getResources().getResourceEntryName(dealType.getId()));
                goToDeals();
            }
        });
    }

    private void goToDeals() {
        Intent i = new Intent(this, DealsActivity.class);
        startActivity(i);
    }
}