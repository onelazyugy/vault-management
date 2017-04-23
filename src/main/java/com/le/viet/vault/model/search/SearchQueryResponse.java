package com.le.viet.vault.model.search;

import com.le.viet.vault.model.common.ServiceResponseStatus;

import java.util.Arrays;

/**
 * Created by onelazyguy on 3/18/17.
 */
public class SearchQueryResponse {
    private QueryResponses[] queryResponses;
    private ServiceResponseStatus serviceResponseStatus;

    public QueryResponses[] getQueryResponses() {
        return queryResponses;
    }

    public void setQueryResponses(QueryResponses[] queryResponses) {
        this.queryResponses = queryResponses;
    }

    public ServiceResponseStatus getServiceResponseStatus() {
        return serviceResponseStatus;
    }

    public void setServiceResponseStatus(ServiceResponseStatus serviceResponseStatus) {
        this.serviceResponseStatus = serviceResponseStatus;
    }

    @Override
    public String toString() {
        return "SearchQueryResponse{" +
                "queryResponses=" + Arrays.toString(queryResponses) +
                ", serviceResponseStatus=" + serviceResponseStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchQueryResponse that = (SearchQueryResponse) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(queryResponses, that.queryResponses)) return false;
        return serviceResponseStatus != null ? serviceResponseStatus.equals(that.serviceResponseStatus) : that.serviceResponseStatus == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(queryResponses);
        result = 31 * result + (serviceResponseStatus != null ? serviceResponseStatus.hashCode() : 0);
        return result;
    }
}
