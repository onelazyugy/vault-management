package com.le.viet.vault.model.entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by onelazyguy on 12/31/16.
 */
@Document(collection = "admindocument")
public class AdminEntry {
    @Id
    private String id;
    private String category;
    private String tag;
    private String password;
    private String username;
    private String comment;
    private String masterPassword;
    private String masterUsername;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public String getMasterUsername() {
        return masterUsername;
    }

    public void setMasterUsername(String masterUsername) {
        this.masterUsername = masterUsername;
    }

    @Override
    public String toString() {
        return "AdminEntry{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", tag='" + tag + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", masterPassword='" + masterPassword + '\'' +
                ", masterUsername='" + masterUsername + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
