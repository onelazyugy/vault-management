package com.le.viet.vault.dao;

import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.AdminEntry;
import com.le.viet.vault.model.SearchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by onelazyguy on 3/18/17.
 */
@Service
public class SearchDaoImpl implements Dao<SearchQuery>{
    private final Logger LOG = LoggerFactory.getLogger(SearchDaoImpl.class);

    @Override
    public boolean add(SearchQuery search) throws VaultException {
        return false;
    }

    @Override
    public boolean update(SearchQuery search) throws VaultException {
        return false;
    }

    @Override
    public boolean delete(SearchQuery search) throws VaultException {
        return false;
    }

    @Override
    public SearchQuery[] retrieveList(SearchQuery search) throws VaultException {
        return new SearchQuery[0];
    }

    @Override
    public SearchQuery retrieve() throws VaultException {
        return null;
    }

    @Override
    public AdminEntry[] retrieveEntries() throws VaultException {
        return new AdminEntry[0];
    }

    @Override
    public boolean verify(SearchQuery search) throws VaultException {
        return false;
    }
}
