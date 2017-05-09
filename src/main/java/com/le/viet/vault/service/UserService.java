package com.le.viet.vault.service;

import com.le.viet.vault.dao.UserDao;
import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.auth.User;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.util.Utils;
import com.le.viet.vault.validation.GeneralValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.le.viet.vault.model.common.Common.*;

/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class UserService {
    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public boolean login(User user, HttpServletRequest req)throws VaultException {
        LOG.debug("STARTED: login");
        boolean isLoginSuccess = false;
        //validate the login credentials
        boolean isLoginDataValid = GeneralValidation.isLoginDataValid(user);
        if(isLoginDataValid) {
            //check against mongodb
            boolean isValidUser = userDao.verifyUser(user);
            //establish a session
            if(isValidUser){
                HttpSession session = req.getSession(true);
                if (session != null) {
                    session.setAttribute("currentLoggedInUser", user.getUsername());
                    session.setAttribute("hasSession", true);
                    isLoginSuccess = true;
                }
            }
        }
        LOG.debug("END: login ==> " + isLoginSuccess);
        return isLoginSuccess;
    }

    public boolean logout(HttpServletRequest req){
        LOG.debug("STARTED: logout");
        try{
            HttpSession session = req.getSession(false);
            session.removeAttribute("hasSession");
            session.removeAttribute("currentLoggedInUser");
            session.invalidate();
        } catch (Exception e){
            return false;
        }
        LOG.debug("END: logout");
        return true;
    }

    public boolean verifyUser(User user) throws ServiceException{
        boolean isValidUser;
        try{
            isValidUser = userDao.verifyUser(user);
        } catch (DaoException de){
            throw new ServiceException(de.getMessage(), DAO_EXCEPTION_CD);
        }
        return isValidUser;
    }

    public void verifyUser(HttpServletRequest req, String masterPassword) throws ServiceException{
        try{
            User user = new User();
            HttpSession session = req.getSession(false);
            if(session != null){
                String currentUser = (String)session.getAttribute("currentLoggedInUser");
                boolean hasSession = (boolean)session.getAttribute("hasSession");
                user.setUsername(currentUser);
                user.setUserLogin(hasSession);
                user.setPassword(masterPassword);
            }
            boolean isValidUser = userDao.verifyUser(user);
            if(!isValidUser){
                throw new ServiceException("master password invalid", SERVICE_EXCEPTION_CD);
            }
        } catch (DaoException de){
            throw new ServiceException(de.getMessage(), DAO_EXCEPTION_CD);
        }
    }

    public void addUser(User user) throws ServiceException{
        try{
            userDao.addUser(user);
        }catch (DaoException de){
            throw new ServiceException(de.getMessage(), DAO_EXCEPTION_CD);
        }
    }
}
