package com.le.viet.vault.model.export;

import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.entry.AdminEntry;

import java.util.Arrays;

/**
 * Created by VTL on 6/11/17.
 */
public class EntryExportResponse {
    private AdminEntry[] adminEntries;
    private ServiceResponseStatus serviceResponseStatus;
    private int totalEntry;
    private String username;

    public AdminEntry[] getAdminEntries() {
        return adminEntries;
    }

    public void setAdminEntries(AdminEntry[] adminEntries) {
        this.adminEntries = adminEntries;
    }

    public ServiceResponseStatus getServiceResponseStatus() {
        return serviceResponseStatus;
    }

    public void setServiceResponseStatus(ServiceResponseStatus serviceResponseStatus) {
        this.serviceResponseStatus = serviceResponseStatus;
    }

    public int getTotalEntry() {
        return totalEntry;
    }

    public void setTotalEntry(int totalEntry) {
        this.totalEntry = totalEntry;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "EntryExportResponse{" +
                "adminEntries=" + Arrays.toString(adminEntries) +
                ", serviceResponseStatus=" + serviceResponseStatus +
                ", totalEntry=" + totalEntry +
                ", username='" + username + '\'' +
                '}';
    }
}
