package com.le.viet.vault.dao;

import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.model.entry.AdminEntry;

import java.util.List;

/**
 * Created by associate on 4/28/17.
 */
public interface AdminDao {
    void addEntry(AdminEntry adminEntry) throws DaoException;
    void addEntries(List<AdminEntry> entries) throws DaoException;

    void removeEntry(AdminEntry adminEntry) throws DaoException;
    void removeEntries(List<AdminEntry> entries) throws DaoException;

    AdminEntry retrieveEntry(String id) throws DaoException;
    AdminEntry[] retrieveEntries(String username) throws DaoException;
}
