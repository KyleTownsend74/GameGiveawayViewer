package com.example.gamedealtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gamedealtracker.adapters.GiveawaysAdapter;
import com.example.gamedealtracker.models.Giveaway;

import java.util.ArrayList;
import java.util.List;

public class GiveawaysActivity extends AppCompatActivity {

    private List<Giveaway> giveaways;
    private RecyclerView rvGiveaways;
    private GiveawaysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveaways);

        rvGiveaways = findViewById(R.id.rvGiveaways);

        giveaways = new ArrayList<>();
        adapter = new GiveawaysAdapter(this, giveaways);

        rvGiveaways.setAdapter(adapter);
        rvGiveaways.setLayoutManager(new LinearLayoutManager(this));
    }
}