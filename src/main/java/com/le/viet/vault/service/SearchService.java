package com.le.viet.vault.service;

import com.le.viet.vault.dao.SearchDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class SearchService {
    @Autowired
    private SearchDaoImpl searchDao;
    private final Logger LOG = LoggerFactory.getLogger(SearchService.class);

}
