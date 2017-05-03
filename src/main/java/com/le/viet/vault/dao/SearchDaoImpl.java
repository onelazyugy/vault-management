package com.le.viet.vault.dao;

import com.le.viet.vault.model.entry.AdminEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by associate on 4/28/17.
 */
@Service
public class SearchDaoImpl implements SearchDao {
    private final Logger LOG = LoggerFactory.getLogger(SearchDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AdminEntry> search(String[] searchTags) {
        LOG.info("STARTED: search: " + Arrays.toString(searchTags));
        List<AdminEntry> adminEntryList = mongoTemplate.findAll(AdminEntry.class);
        List<AdminEntry> adminEntryFoundList = new ArrayList<>();
        Set<AdminEntry> adminEntrySet = new HashSet<>();
        for(String searchTag : searchTags){
            for(AdminEntry adminEntry : adminEntryList){
                if(adminEntry.getTag().contains(searchTag)){
                    adminEntrySet.add(adminEntry);
                }
            }
        }
        adminEntryFoundList.addAll(adminEntrySet);
        Comparator<AdminEntry> adminEntryComparator = Comparator.comparing(AdminEntry::getTag);
        Collections.sort(adminEntryFoundList, adminEntryComparator);
        LOG.info("END: search: adminEntryFoundList" + adminEntryFoundList.toString());
        return adminEntryFoundList;
    }
}
