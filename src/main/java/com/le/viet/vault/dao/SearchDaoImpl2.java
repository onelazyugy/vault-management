package com.le.viet.vault.dao;

import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.SearchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by associate on 4/28/17.
 */
public class SearchDaoImpl2 implements SearchDao {
    private final Logger LOG = LoggerFactory.getLogger(SearchDaoImpl2.class);

    @Autowired
    private SearchDao searchDao;

    @Override
    public AdminEntry[] search(SearchQuery searchQuery) {
        return new AdminEntry[0];
    }
}
