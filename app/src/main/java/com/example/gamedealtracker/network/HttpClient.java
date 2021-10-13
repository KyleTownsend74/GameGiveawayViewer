package com.example.gamedealtracker.network;

import okhttp3.OkHttpClient;

// Class to manage OkHttp resources
public class HttpClient {

    private static OkHttpClient client;

    public static OkHttpClient getClient() {
        if(client == null) {
            client = new OkHttpClient();
        }

        return client;
    }
}
