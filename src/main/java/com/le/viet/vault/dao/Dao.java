package com.le.viet.vault.dao;

import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;

/**
 * Created by onelazyguy on 12/29/16.
 */
public interface Dao<T> {
    boolean add(T t) throws VaultException;
    boolean update(T t) throws VaultException;
    boolean delete(T t) throws VaultException;
    T[] retrieveList(T t) throws VaultException;
    T retrieve() throws VaultException;
    AdminEntry[] retrieveEntries() throws VaultException;
    boolean verify(T t) throws VaultException;
}
