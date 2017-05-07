package com.le.viet.vault.model.search;

import com.le.viet.vault.model.common.ServiceResponseStatus;

/**
 * Created by onelazyguy on 5/7/17.
 */
public class SearchPromptResponse {
    private ServiceResponseStatus serviceResponseStatus;
    private String password;

    public ServiceResponseStatus getServiceResponseStatus() {
        return serviceResponseStatus;
    }

    public void setServiceResponseStatus(ServiceResponseStatus serviceResponseStatus) {
        this.serviceResponseStatus = serviceResponseStatus;
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

        SearchPromptResponse that = (SearchPromptResponse) o;

        if (serviceResponseStatus != null ? !serviceResponseStatus.equals(that.serviceResponseStatus) : that.serviceResponseStatus != null)
            return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = serviceResponseStatus != null ? serviceResponseStatus.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchPromptResponse{" +
                "serviceResponseStatus=" + serviceResponseStatus +
                ", password='" + password + '\'' +
                '}';
    }
}
