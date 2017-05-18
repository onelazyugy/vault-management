package com.le.viet.vault.dao;

import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.model.entry.AdminEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static com.le.viet.vault.model.common.Common.DAO_EXCEPTION_CD;

/**
 * Created by associate on 4/28/17.
 */
@Service
public class SearchDaoImpl implements SearchDao {
    private final Logger LOG = LoggerFactory.getLogger(SearchDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AdminEntry> search(String[] searchTags, String currentUser) {
        LOG.info("STARTED: search: " + Arrays.toString(searchTags));
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("masterUsername").in(currentUser));
        query.addCriteria(criteria);
        List<AdminEntry> adminEntryList = mongoTemplate.find(query, AdminEntry.class);
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

    @Override
    public AdminEntry retrieveEntry(String id) throws DaoException {
        try {
            AdminEntry adminEntry = this.mongoTemplate.findById(id, AdminEntry.class);
            return adminEntry;
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("Exception: " + e.getMessage());
            throw new DaoException(e.getMessage(), DAO_EXCEPTION_CD);
        }
    }
}
