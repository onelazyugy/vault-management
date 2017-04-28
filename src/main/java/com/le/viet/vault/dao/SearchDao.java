package com.le.viet.vault.dao;

import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.SearchQuery;

/**
 * Created by associate on 4/28/17.
 */
public interface SearchDao {
    AdminEntry[] search(SearchQuery searchQuery);
}
