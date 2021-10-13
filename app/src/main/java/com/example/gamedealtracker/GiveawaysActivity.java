package com.example.gamedealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.gamedealtracker.adapters.GiveawaysAdapter;
import com.example.gamedealtracker.models.Giveaway;
import com.example.gamedealtracker.network.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class GiveawaysActivity extends AppCompatActivity {

    private static final String TAG = "GiveawaysActivity";
    private static final String BASE_URL = "https://www.gamerpower.com/api/giveaways";

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

        getGiveaways();
    }

    private void getGiveaways() {
        // Create request
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        // Send request asynchronously
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(!response.isSuccessful()) {
                    GiveawaysActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(GiveawaysActivity.this, "Error loading giveaways.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }

                try {
                    // Convert data to JSON array
                    JSONArray dataArray = new JSONArray(response.body().string());

                    // Loop through array, converting each object to POJO
                    for(int i = 0; i < dataArray.length(); i++) {
                        giveaways.add(Giveaway.fromJsonObject(dataArray.getJSONObject(i)));
                    }

                    GiveawaysActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing JSON", e);
                }
            }
        });
    }
}