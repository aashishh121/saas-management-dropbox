package com.cloudeagle.dropbox.model;

public class TokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
