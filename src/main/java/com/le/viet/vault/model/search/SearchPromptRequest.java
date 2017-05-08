package com.le.viet.vault.model.search;

/**
 * Created by onelazyguy on 5/7/17.
 */
public class SearchPromptRequest {
    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchPromptRequest that = (SearchPromptRequest) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchPromptRequest{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
