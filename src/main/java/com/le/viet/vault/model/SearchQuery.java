package com.le.viet.vault.model;

/**
 * Created by onelazyguy on 3/18/17.
 */
public class SearchQuery {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "query='" + query + '\'' +
                '}';
    }
}
