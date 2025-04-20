package com.deepak.github_user_activity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Github {
    private final HttpClient httpClient;

    public Github() {
        httpClient = HttpClient.newHttpClient();
    }
    public String fetchUserActivity(String username) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/" + username + "/events"))
                .GET()
                .build();
        var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();
    }

    public List<GithubActivity> parseUserActivity(String httpResponse) {
        List<GithubActivity> githubActivities = new ArrayList<>();
        JsonElement jsonElement = JsonParser.parseString(httpResponse);
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        for(JsonElement jsonElement1: asJsonArray) {
            JsonObject jsonObject = jsonElement1.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            String repoName = jsonObject.get("repo").getAsJsonObject().get("name").getAsString();
            boolean added = false;
            for(GithubActivity activity: githubActivities) {
                if(activity.getType().equals(type) && activity.getRepo().equals(repoName)) {
                    activity.setCount(activity.getCount() + 1);
                    added = true;
                }
            }
            if(!added){
                GithubActivity githubActivity = new GithubActivity();
                githubActivity.setType(type);
                githubActivity.setRepo(repoName);
                githubActivity.setCount(1);
                githubActivities.add(githubActivity);
            }
        }
        return githubActivities;
    }

    public void printGithubActivities(List<GithubActivity> githubActivities) {
        System.out.println("Output : ");
        githubActivities.forEach(githubActivity -> {
            System.out.println(githubActivity.getCount() + " " + githubActivity.getType() + " in the repo " + githubActivity.getRepo());
        });
    }

    public void doTask(String username) throws IOException, InterruptedException {
        String httpResponse = fetchUserActivity(username);
        List<GithubActivity> githubActivities = parseUserActivity(httpResponse);
        printGithubActivities(githubActivities);
    }
}
