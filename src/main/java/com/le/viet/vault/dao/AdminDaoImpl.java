package com.le.viet.vault.dao;

import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.le.viet.vault.model.common.Common.*;

/**
 * Created by associate on 4/28/17.
 */
@Service
public class AdminDaoImpl implements AdminDao{
    private final Logger LOG = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addEntry(AdminEntry adminEntry) throws DaoException {
        LOG.info("STARTED: addEntry, adminEntry: " + adminEntry.toString());
        try {
            adminEntry.setDateTime(new Date().toString());
            this.mongoTemplate.insert(adminEntry);
        } catch (Exception e){
            LOG.error("EXCEPTION: from addEntry, " + e.getMessage());
            throw new DaoException(e.getMessage(), DAO_EXCEPTION_CD);
        }
        LOG.info("END: addEntry");
    }

    @Override
    public void addEntries(List<AdminEntry> entries) throws DaoException {

    }

    @Override
    public void removeEntry(AdminEntry adminEntry) throws DaoException {

    }

    @Override
    public void removeEntries(List<AdminEntry> entries) throws DaoException {

    }

    @Override
    public AdminEntry retrieveEntry() throws DaoException {
        return null;
    }

    @Override
    public AdminEntry[] retrieveEntries() throws DaoException {
        LOG.info("STARTED: retrieveEntries");
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
                String user = map.get("username").toString();
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
            LOG.info("END: retrieveEntries, entries: " + Arrays.toString(entries));
            return entries;
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("Exception: " + e.getMessage());
            throw new DaoException(e.getMessage(), DAO_EXCEPTION_CD);
        }
    }
}
