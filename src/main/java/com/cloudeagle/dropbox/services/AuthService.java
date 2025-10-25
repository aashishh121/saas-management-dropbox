package com.cloudeagle.dropbox.services;

import com.cloudeagle.dropbox.config.AppConfig;
import com.cloudeagle.dropbox.model.TokenResponse;
import okhttp3.*;
import com.google.gson.Gson;

public class AuthService {

    private static final String AUTH_URL = "https://www.dropbox.com/oauth2/authorize";
    private static final String TOKEN_URL = "https://api.dropboxapi.com/oauth2/token";
    private static final Gson gson = new Gson();

    public static String getAuthorizationUrl() {
        String clientId = AppConfig.get("client.id");
        String redirectUri = AppConfig.get("redirect.uri");
        return AUTH_URL + "?response_type=code"
                + "&client_id=" + clientId
                + "&scope=team_info.read members.read events.read team_data.member account_info.read"
                + "&redirect_uri=" + redirectUri;
    }

    public static TokenResponse getAccessToken(String authCode) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody form = new FormBody.Builder()
                .add("code", authCode)
                .add("grant_type", "authorization_code")
                .add("redirect_uri", AppConfig.get("redirect.uri"))
                .add("client_id", AppConfig.get("client.id"))
                .add("client_secret", AppConfig.get("client.secret"))
                .build();

        Request request = new Request.Builder()
                .url(TOKEN_URL)
                .post(form)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new RuntimeException("Token request failed: " + response.code() + " " + response.message());
            }
            String body = response.body().string();
            return gson.fromJson(body, TokenResponse.class);
        }
    }
}
