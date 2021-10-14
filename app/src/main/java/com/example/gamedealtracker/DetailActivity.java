package com.example.gamedealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
    private TextView tvDetailLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        ivDetailThumbnail = findViewById(R.id.ivDetailThumbnail);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailLink = findViewById(R.id.tvDetailLink);

        curGiveaway = Parcels.unwrap(getIntent().getParcelableExtra("giveaway"));

        // Call method to set up UI
        setUpUi();
    }

    // Sets up DetailActivity UI with proper content from curGiveaway
    private void setUpUi() {
        // Set up image view
        Glide.with(this)
                .load(curGiveaway.getThumbnailUrl())
                .placeholder(new ColorDrawable(Color.LTGRAY))
                .into(ivDetailThumbnail);

        // Set up text views
        tvDetailTitle.setText(curGiveaway.getTitle());
        tvDetailDescription.setText("Description: " + curGiveaway.getDescription());
        tvDetailLink.setText(Html.fromHtml(
                String.format("<a href=\"%s\">Link to Giveaway</a>", curGiveaway.getGiveawayUrl())));
        tvDetailLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}