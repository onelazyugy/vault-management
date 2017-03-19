package com.le.viet.vault.model;

/**
 * Created by onelazyguy on 3/18/17.
 */
public class SearchQueryResponse {
    private String tag;
    private String username;
    private String password;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SearchQueryResponse{" +
                "tag='" + tag + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
