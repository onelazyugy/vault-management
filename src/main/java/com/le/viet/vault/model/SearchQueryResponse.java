package com.le.viet.vault.model;

/**
 * Created by onelazyguy on 3/18/17.
 */
public class SearchQueryResponse {
    private String tag;
    private String username;
    private String password;
    private boolean isSuccess;
    private String message;

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

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchQueryResponse response = (SearchQueryResponse) o;

        if (isSuccess != response.isSuccess) return false;
        if (tag != null ? !tag.equals(response.tag) : response.tag != null) return false;
        if (username != null ? !username.equals(response.username) : response.username != null) return false;
        if (password != null ? !password.equals(response.password) : response.password != null) return false;
        return message != null ? message.equals(response.message) : response.message == null;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isSuccess ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchQueryResponse{" +
                "tag='" + tag + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }

}
