package com.le.viet.vault.controller;

import com.le.viet.vault.dao.UserDaoImpl;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.User;
import com.le.viet.vault.user.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rs")
public class VaultController {
	private final Logger LOG = LoggerFactory.getLogger(VaultController.class);
	@Autowired UserAuth userAuth;
	@Autowired
	UserDaoImpl userDao;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping(){
		return "pong";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean login(@RequestBody User user, HttpServletRequest req){
		LOG.debug("STARTED: /login");
		boolean loginSuccess = false;
		try{
			loginSuccess = userAuth.login(user, req);
		}catch (VaultException ve){
			ve.printStackTrace();
			LOG.error("VaultException: " + ve.getMessage());
		}
		LOG.debug("END: /login ==> " + loginSuccess);
		return loginSuccess;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean logout(HttpServletRequest req){
		LOG.debug("STARTED: /logout");
		boolean result = userAuth.logout(req);
		LOG.debug("END: /logout ==> " + result);
		return result;
	}

	@RequestMapping(value = "/userStillAlive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User userStillAlive(HttpServletRequest req){
		LOG.debug("STARTED: /userStillAlive");
		HttpSession session = req.getSession(false);
		User user = new User();
		if(session != null){
			String currentUser = (String)session.getAttribute("currentLoggedInUser");
			boolean hasSession = (boolean)session.getAttribute("hasSession");
			user.setUsername(currentUser);
			user.setUserLogin(hasSession);
		}
		LOG.debug("END: /userStillAlive ==> " + user.toString());
		return user;
	}

    @RequestMapping(value = "/isUserLoggedIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean isUserLoggedIn(HttpServletRequest req){
		LOG.debug("STARTED: /isUserLoggedIn");
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = false;
        if(session != null){
            isLoggedIn = (boolean)session.getAttribute("hasSession");
        }
		LOG.debug("STARTED: /isUserLoggedIn ==> " + isLoggedIn);
        return isLoggedIn;
    }

	@RequestMapping(value = "/getUserStatus", method = RequestMethod.POST)
	public boolean getUserStatus(@RequestBody User user){
    	boolean isUserValid = false;
    	try {
			isUserValid = userDao.verify(user);
		} catch (VaultException ve){
			ve.printStackTrace();
			LOG.error("VaultException: " + ve.getMessage());
		}
		return isUserValid;

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public boolean addUser(@RequestBody User user){
		boolean isUserValid = false;
		try {
			isUserValid = userDao.add(user);
		}catch (VaultException ve){
			ve.printStackTrace();
			LOG.error("VaultException: " + ve.getMessage());
		}
		return isUserValid;
	}
}
