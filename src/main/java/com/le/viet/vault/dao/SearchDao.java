package com.le.viet.vault.dao;

import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.SearchQuery;

import java.util.List;

/**
 * Created by associate on 4/28/17.
 */
public interface SearchDao {
    List<AdminEntry> search(String[] searchTags);
    AdminEntry retrieveEntry(String id) throws DaoException;
}
