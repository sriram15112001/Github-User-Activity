package com.deepak.github_user_activity;

import java.io.IOException;

public class GithubUserActivityApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        Github github = new Github();
        github.doTask(args[0]);
    }
}
