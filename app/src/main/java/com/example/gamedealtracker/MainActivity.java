package com.example.gamedealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private View dealTypePC;
    private View dealTypePS;
    private View dealTypeXB;
    private View dealTypeMisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize interactive deal types
        dealTypePC = findViewById(R.id.dealTypePC);
        dealTypePS = findViewById(R.id.dealTypePS);
        dealTypeXB = findViewById(R.id.dealTypeXB);
        dealTypeMisc = findViewById(R.id.dealTypeAll);

        // Call method to set up UI
        setUpUi();
    }

    // Sets up interactive deal types with visible content
    private void setUpUi() {
        // Set up PC deal type
        ((ImageView)dealTypePC.findViewById(R.id.ivDealType))
                .setImageDrawable(getDrawable(R.drawable.ic_baseline_computer_24));
        ((TextView)dealTypePC.findViewById(R.id.tvDealType))
                .setText("Browse deals for PC");

        // Set up PlayStation deal type
        ((ImageView)dealTypePS.findViewById(R.id.ivDealType))
                .setImageDrawable(getDrawable(R.drawable.ic_playstation_logo));
        ((TextView)dealTypePS.findViewById(R.id.tvDealType))
                .setText("Browse deals for PlayStation");

        // Set up XBox deal type
        ((ImageView)dealTypeXB.findViewById(R.id.ivDealType))
                .setImageDrawable(getDrawable(R.drawable.ic_xbox_logo));
        ((TextView)dealTypeXB.findViewById(R.id.tvDealType))
                .setText("Browse deals for XBox");

        // Set up Misc deal type
        ((ImageView)dealTypeMisc.findViewById(R.id.ivDealType))
                .setImageDrawable(getDrawable(R.drawable.ic_baseline_money_24));
        ((TextView)dealTypeMisc.findViewById(R.id.tvDealType))
                .setText("Browse all deals");
    }
}