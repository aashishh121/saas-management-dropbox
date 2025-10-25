package com.cloudeagle.dropbox;

import com.cloudeagle.dropbox.model.TokenResponse;
import com.cloudeagle.dropbox.services.AuthService;
import com.cloudeagle.dropbox.services.DropBoxService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Step 1: Ask user to authorize
            System.out.println("=== Dropbox OAuth2 Demo ===");
            System.out.println("Go to the following URL and authorize access:\n");
            System.out.println(AuthService.getAuthorizationUrl());
            System.out.println("\nAfter approving/authenticated, copy the full redirect URL and paste below.\n");

            Scanner sc = new Scanner(System.in);
            System.out.print("Paste redirect URL here: ");
            String fullUrl = sc.nextLine();

            // Extract the 'code' parameter manually
            String code = extractCode(fullUrl);
            System.out.println("Auth Code: " + code);

            // Step 2: Exchange for access token
            TokenResponse token = AuthService.getAccessToken(code);
            System.out.println("\nAccess Token: " + token.getAccess_token());

            // Step 3: Call Dropbox API
            String response = DropBoxService.getTeamInfo(token.getAccess_token());
            System.out.println("\nTeam Info Response:\n" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractCode(String url) {
        int start = url.indexOf("code=");
        if (start == -1)
            throw new RuntimeException("Code not found in redirect URL");
        int end = url.indexOf("&", start);
        return (end == -1) ? url.substring(start + 5) : url.substring(start + 5, end);
    }
}
