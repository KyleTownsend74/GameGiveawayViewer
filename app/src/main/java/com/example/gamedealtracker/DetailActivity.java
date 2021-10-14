package com.example.gamedealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gamedealtracker.models.Giveaway;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    private Giveaway curGiveaway;
    private TextView tvDetailTitle;
    private ImageView ivDetailThumbnail;
    private TextView tvDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        ivDetailThumbnail = findViewById(R.id.ivDetailThumbnail);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);

        curGiveaway = Parcels.unwrap(getIntent().getParcelableExtra("giveaway"));

        tvDetailTitle.setText(curGiveaway.getTitle());
        tvDetailDescription.setText("Description: " + curGiveaway.getDescription());

        Glide.with(this)
                .load(curGiveaway.getThumbnailUrl())
                .placeholder(new ColorDrawable(Color.LTGRAY))
                .into(ivDetailThumbnail);
    }
}