package com.example.gamedealtracker.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Giveaway {

    String title;
    String description;
    String thumbnailUrl;

    // Create giveaway from Json object
    public static Giveaway fromJsonObject(JSONObject jsonObject) throws JSONException {
        Giveaway giveaway = new Giveaway();

        // Parse JSON
        giveaway.title = jsonObject.getString("title");
        giveaway.description = jsonObject.getString("description");
        giveaway.thumbnailUrl = jsonObject.getString("thumbnail");

        return giveaway;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
