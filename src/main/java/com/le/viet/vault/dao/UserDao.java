package com.le.viet.vault.dao;

import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.model.auth.User;

/**
 * Created by associate on 4/28/17.
 */
public interface UserDao {
    void addUser(User user) throws DaoException;
    void loginUser() throws DaoException;
    boolean verifyUser(User user) throws DaoException;
    User retrieveUser(String userName) throws DaoException;
}
