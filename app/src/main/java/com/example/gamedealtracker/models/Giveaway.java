package com.example.gamedealtracker.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Giveaway {

    private String title;
    private String description;
    private String thumbnailUrl;

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
