
package com.cloudeagle.dropbox.services;

import okhttp3.*;
import com.google.gson.Gson;

public class DropBoxService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static String getTeamInfo(String accessToken) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.dropboxapi.com/2/team/get_info")
                .post(RequestBody.create(new byte[0], null))
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new RuntimeException(
                        "API call failed: " + response.code() + " " + response.message());
            }
            return response.body().string();

        }
    }
}
