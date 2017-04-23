package com.le.viet.vault.model.search;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchQuery that = (SearchQuery) o;

        return query != null ? query.equals(that.query) : that.query == null;
    }

    @Override
    public int hashCode() {
        return query != null ? query.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "query='" + query + '\'' +
                '}';
    }
}
