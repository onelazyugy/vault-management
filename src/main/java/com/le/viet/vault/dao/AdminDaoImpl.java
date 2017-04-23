package com.le.viet.vault.dao;

import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by onelazyguy on 12/31/16.
 */
@Service
public class AdminDaoImpl implements Dao<AdminEntry> {
    private final Logger LOG = LoggerFactory.getLogger(AdminDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean add(AdminEntry adminEntry) throws VaultException{
        boolean isAddEntrySuccess;
        try {
            adminEntry.setDateTime(new Date().toString());
            this.mongoTemplate.insert(adminEntry);
            isAddEntrySuccess = true;
        }catch (Exception e){
            throw new VaultException("Exception: " + e.getMessage(), 0);
        }
        return isAddEntrySuccess;
    }

    @Override
    public AdminEntry[] retrieveEntries() throws VaultException{
        try{
            DBCollection collection = this.mongoTemplate.getCollection("admindocument");
            DBCursor cursor = collection.find();
            int entriesSize = cursor.size();
            AdminEntry[] entries = new AdminEntry[entriesSize];
            for(int i=0; i<entriesSize; i++){
                AdminEntry adminEntry = new AdminEntry();
                DBObject dbObject = cursor.next();
                Map map = dbObject.toMap();
                String id = map.get("_id").toString();
                String tag = map.get("tag").toString();
                String pass = map.get("password").toString();
                String user =map.get("username").toString();
                String comment = map.get("comment").toString();
                String dateString = map.get("dateTime").toString();
                String cat = map.get("category").toString();

                adminEntry.setId(id);
                adminEntry.setTag(tag);
                adminEntry.setPassword(pass);
                adminEntry.setUsername(user);
                adminEntry.setComment(comment);
                adminEntry.setDateTime(dateString);
                adminEntry.setCategory(cat);
                entries[i] = adminEntry;
            }
            LOG.debug("array of entries: " + entries);
            return entries;
        } catch (Exception e){
            e.printStackTrace();
            throw new VaultException("Exception: " + e.getMessage(), 0);
        }
    }

    @Override
    public boolean update(AdminEntry adminEntry) {
        return false;
    }

    @Override
    public boolean delete(AdminEntry adminEntry) {
        return false;
    }

    @Override
    public AdminEntry[] retrieveList(AdminEntry adminEntry) {
        return null;
    }

    @Override
    public AdminEntry retrieve() {
        return null;
    }

    @Override
    public boolean verify(AdminEntry adminEntry) {
        return false;
    }
}
