package com.deepak.github_user_activity;

public class GithubActivity {
    private String type;
    private String repo;
    private int count;

    public GithubActivity() {
    }

    public GithubActivity(String type, String repo, int count) {
        this.type = type;
        this.repo = repo;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GithubActivity{");
        sb.append("type='").append(type).append('\'');
        sb.append(", repo='").append(repo).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
