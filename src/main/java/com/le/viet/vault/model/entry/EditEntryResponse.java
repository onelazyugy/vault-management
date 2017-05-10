package com.le.viet.vault.model.entry;

import java.util.Arrays;

/**
 * Created by onelazyguy on 5/9/17.
 */
public class EditEntryResponse {
    private AdminEntry[] adminEntries;
    private AdminEntryResponse adminEntryResponse;

    public AdminEntry[] getAdminEntries() {
        return adminEntries;
    }

    public void setAdminEntries(AdminEntry[] adminEntries) {
        this.adminEntries = adminEntries;
    }

    public AdminEntryResponse getAdminEntryResponse() {
        return adminEntryResponse;
    }

    public void setAdminEntryResponse(AdminEntryResponse adminEntryResponse) {
        this.adminEntryResponse = adminEntryResponse;
    }

    @Override
    public String toString() {
        return "EditEntryResponse{" +
                "adminEntries=" + Arrays.toString(adminEntries) +
                ", adminEntryResponse=" + adminEntryResponse +
                '}';
    }
}
