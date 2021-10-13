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

    private View giveawayTypePC;
    private View giveawayTypePS;
    private View giveawayTypeXB;
    private View giveawayTypeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize interactive giveaway types
        giveawayTypePC = findViewById(R.id.giveawayTypePC);
        giveawayTypePS = findViewById(R.id.giveawayTypePS);
        giveawayTypeXB = findViewById(R.id.giveawayTypeXB);
        giveawayTypeAll = findViewById(R.id.giveawayTypeAll);

        // Call method to set up UI
        setUpUi();
    }

    // Sets up interactive giveaway types with UI content
    private void setUpUi() {
        // Set up PC giveaway type
        setSingleTypeUi(giveawayTypePC, getDrawable(R.drawable.ic_baseline_computer_24),
                "Browse giveaways for PC");

        // Set up PlayStation giveaway type
        setSingleTypeUi(giveawayTypePS, getDrawable(R.drawable.ic_playstation_logo),
                "Browse giveaways for PlayStation 4");

        // Set up XBox giveaway type
        setSingleTypeUi(giveawayTypeXB, getDrawable(R.drawable.ic_xbox_logo),
                "Browse giveaways for Xbox One");

        // Set up Misc giveaway type
        setSingleTypeUi(giveawayTypeAll, getDrawable(R.drawable.ic_baseline_money_24),
                "Browse all giveaways deals");
    }

    // Set up UI for a single giveaway type
    private void setSingleTypeUi(View giveawayType, Drawable icon, String msg) {
        // Set up visual aspects
        ((ImageView)giveawayType.findViewById(R.id.ivGiveawayType)).setImageDrawable(icon);
        ((TextView)giveawayType.findViewById(R.id.tvGiveawayType)).setText(msg);

        // Set up click listener
        giveawayType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get type of giveaway passed in
                String type = giveawayType.getResources().getResourceEntryName(giveawayType.getId());

                // Pass in correct platform when going to GiveawaysActivity
                switch(type) {
                    case "giveawayTypePC":
                        goToGiveaways("pc");
                        break;
                    case "giveawayTypePS":
                        goToGiveaways("ps4");
                        break;
                    case "giveawayTypeXB":
                        goToGiveaways("xbox-one");
                        break;
                    case "giveawayTypeAll":
                        goToGiveaways("");
                        break;
                    default:
                        Log.e(TAG, "Unknown platform.");
                }
            }
        });
    }

    // Go to GiveawaysActivity with passed in platform
    private void goToGiveaways(String platform) {
        Intent i = new Intent(this, GiveawaysActivity.class);

        if(platform != null) {
            i.putExtra("platform", platform);
        }

        startActivity(i);
    }
}