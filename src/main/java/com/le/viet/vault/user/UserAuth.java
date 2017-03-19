package com.le.viet.vault.user;

import com.le.viet.vault.dao.UserDao;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.User;
import com.le.viet.vault.validation.GeneralValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by onelazyguy on 12/29/16.
 */
@Service
public class UserAuth {
    private final Logger LOG = LoggerFactory.getLogger(UserAuth.class);
    @Autowired
    private UserDao userDao;
    public boolean login(User user, HttpServletRequest req)throws VaultException{
        LOG.debug("STARTED: login");
        boolean isLoginSuccess = false;
        //validate the login credentials
        boolean isLoginDataValid = GeneralValidation.isLoginDataValid(user);
        if(isLoginDataValid) {
            //check against mongodb
            boolean isValidUser = userDao.verify(user);
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
        }catch (Exception e){
            return false;
        }
        LOG.debug("END: logout");
        return true;
    }
}
