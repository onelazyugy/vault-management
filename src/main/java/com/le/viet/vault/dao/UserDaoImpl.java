package com.le.viet.vault.dao;

import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.auth.User;
import com.le.viet.vault.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by onelazyguy on 12/29/16.
 */
@Service
public class UserDaoImpl implements Dao<User> {
    private final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean add(User user) throws VaultException{
        user.setCreatedDate(new Date());
        Utils utils = new Utils();
        boolean addUserSuccess = false;
        try{
            String hashedPassword = utils.hash(user.getPassword());
            user.setPassword(hashedPassword);
            this.mongoTemplate.insert(user);
            addUserSuccess = true;
            return addUserSuccess;
        } catch (Exception e){
            LOG.error("Exception in add():\n" + e.getMessage());
            e.printStackTrace();
            throw new VaultException("exception: " + e.getMessage(), 0);
        }
    }

    @Override
    public boolean verify(User user) throws VaultException{
        boolean isUserFound = false;
        Query query = new Query(Criteria.where("username").is(user.getUsername()));
        User foundUser = this.mongoTemplate.findOne(query, User.class);
        if(foundUser != null ){
            try {
                Utils utils = new Utils();
                String inputHashedPass = utils.hash(user.getPassword());
                String storedHashedPass = foundUser.getPassword();
                String storedUserName = foundUser.getUsername();
                if(storedUserName.equals(user.getUsername()) && inputHashedPass.equals(storedHashedPass)){
                    LOG.debug("foundUser ==>: " + foundUser.toString());
                    isUserFound = true;
                }
            } catch (VaultException pe){
                return isUserFound;
            }
        }
        return isUserFound;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User[] retrieveList(User user) {
        return null;
    }

    @Override
    public User retrieve() {
        return null;
    }

    @Override
    public AdminEntry[] retrieveEntries() {
        return null;
    }
}
