package com.le.viet.vault.dao;

import com.le.viet.vault.exception.DaoException;
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

import static com.le.viet.vault.model.common.Common.DAO_EXCEPTION_CD;

/**
 * Created by associate on 4/28/17.
 */
@Service
public class UserDaoImpl implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addUser(User user) throws DaoException {
        user.setCreatedDate(new Date());
        try{
            String hashedPassword = Utils.hash(user.getPassword());
            user.setPassword(hashedPassword);
            this.mongoTemplate.insert(user);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("Exception in add():\n" + e.getMessage());
            throw new DaoException("exception: " + e.getMessage(), DAO_EXCEPTION_CD);
        }
    }

    @Override
    public void loginUser() throws DaoException{

    }

    @Override
    public boolean verifyUser(User user) throws DaoException{
        LOG.info("STARTED: verfiyUser");
        boolean isUserFound = false;
        Query query = new Query(Criteria.where("username").is(user.getUsername()));
        User foundUser = this.mongoTemplate.findOne(query, User.class);
        if(foundUser != null ){
            try {
                String inputHashedPass = Utils.hash(user.getPassword());
                String storedHashedPass = foundUser.getPassword();
                String storedUserName = foundUser.getUsername();
                if(storedUserName.equals(user.getUsername()) && inputHashedPass.equals(storedHashedPass)){
                    LOG.debug("foundUser ==>: " + foundUser.toString());
                    isUserFound = true;
                }
            } catch (Exception e){
                LOG.error("Exception: " + e.getMessage());
                return isUserFound;
            }
        }
        LOG.info("END: verfiyUser: " + isUserFound);
        return isUserFound;
    }
}
