package com.example.gamedealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class GiveawaysActivity extends AppCompatActivity {

    private static final String TAG = "GiveawaysActivity";
    private static final String BASE_URL = "https://www.gamerpower.com/api/giveaways";

    private TextView tvDisplayType;
    private List<Giveaway> giveaways;
    private RecyclerView rvGiveaways;
    private GiveawaysAdapter adapter;
    private String platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveaways);

        tvDisplayType = findViewById(R.id.tvDisplayType);
        rvGiveaways = findViewById(R.id.rvGiveaways);

        // Set up recycler view and its content to use
        giveaways = new ArrayList<>();
        adapter = new GiveawaysAdapter(this, giveaways);
        rvGiveaways.setAdapter(adapter);
        rvGiveaways.setLayoutManager(new LinearLayoutManager(this));

        // Set up display platform text
        String baseText = "Giveaways for ";
        platform = getIntent().getStringExtra("platform");
        switch (platform) {
            case "pc":
                tvDisplayType.setText(baseText + "PC");
                break;
            case "ps4":
                tvDisplayType.setText(baseText + "PlayStation 4");
                break;
            case "xbox-one":
                tvDisplayType.setText(baseText + "Xbox One");
                break;
            default:
                tvDisplayType.setText(baseText + "All Platforms");
        }

        // Make request for giveaways
        getGiveaways();
    }

    // Get giveaways from API and display to user
    private void getGiveaways() {
        // Create URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        if(!platform.isEmpty()) {
            urlBuilder.addQueryParameter("platform", platform);
        }
        String finalUrl = urlBuilder.build().toString();

        // Create request
        Request request = new Request.Builder()
                .url(finalUrl)
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

                    // Update recycler view on UI thread
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