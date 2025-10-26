# Dropbox Business API Integration (CloudEagle Assessment)

This project demonstrates how to integrate Dropbox Business APIs using Java Command-line.
It covers OAuth2 authentication and fetching organization/team information via Dropbox’s API.

# Features

    - OAuth2 Authorization Code flow (Dropbox Business)
    - Secure configuration loading from application.properties
    - Fetch Team/Organization info from Dropbox API
    - Simple command-line interface for testing authentication flow

# Technologies Used

    - Language: Java 11+
    - Build Tool: Maven
    - HTTP Client: OkHttp 4.9.3
    - API Tested With: Postman
    - Auth Type: OAuth2 (Authorization Code Flow)

# Prerequisites
 Before running this project, make sure you have the following installed:
    
    Java JDK 11+
    Apache Maven 3.6+
    Git
    Dropbox Developer Account

# Setup Instructions
Step 1
Clone this repository and navigate to the project directory:

Step 2
Configure Dropbox credentials
Create a file named application.properties inside src/main/resources/ and add:
 ```
    client.id=YOUR_APP_CLIENT_ID
    client.secret=YOUR_APP_CLIENT_SECRET
    redirect.uri=YOUR_REDIRECT_URL
 ```

Step 3 
Build and Run using Maven
```
mvn clean install
mvn clean compile exec:java
```

# OAuth Flow

    - The app prints an authorization URL.
    - Open it, log in to Dropbox, and allow access.
    - Copy the full redirect URL (it contains code=...).
    - Paste it back into the terminal.
    - The app exchanges it for an access token and fetches team info.

# Example Output

  === Dropbox OAuth2 Demo ===
  
    Go to the following URL and authorize access:
    
    https://www.dropbox.com/oauth2/authorize?response_type=code&client_id=abc123&redirect_uri=http://localhost:8080/callback
    
    Paste redirect URL here:
    >>> postman://app/oauth2/callback?code=Uq4RB9DTUPsAAAAAAAAAVKHzabagp0t1gdkJO0eEpVs
    
    Access Token: sl.BCDeFgHiJkLmNoP
    
    Team Info Response:
    {
    "name": "CloudEagle Test Org",
    "team_id": "dbtid:12345example"
    }

# Project Structure
```
src/
 └── main/
      └── java/
          └── com/cloudeagle/dropbox/
              ├── Main.java
              ├── config/
              │    └── AppConfig.java
              ├── model/
              │    └── TokenResponse.java
              └── services/
                   ├── AuthService.java
                   └── DropBoxService.java
      └── resources/
          └── application.properties
```

# Notes

  -> Add real credentials in your local environment only.
  -> The redirect URI in your Dropbox app must exactly match the one in your config file.
