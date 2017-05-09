package com.le.viet.vault.controller;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.entry.AdminEntryResponse;
import com.le.viet.vault.service.AdminService;
import com.le.viet.vault.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by associate on 4/28/17.
 */
@RestController
@RequestMapping("/rs")
public class AdminController {
    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AdminEntryResponse addEntry(@RequestBody AdminEntry adminEntry, HttpServletRequest req){
        LOG.info("STARTED: /addEntry: " + adminEntry.toString());
        AdminEntryResponse adminEntryResponse = new AdminEntryResponse();
        try{
            userService.verifyUser(req, adminEntry.getMasterPassword().trim());
            adminService.addEntry(adminEntry);
            adminEntryResponse.setMessage("success");
            adminEntryResponse.setSuccess(true);
        } catch (ServiceException se){
            adminEntryResponse.setMessage(se.getMessage());
            adminEntryResponse.setSuccess(false);
            LOG.error("ServiceException: " + se.getMessage());
        }
        LOG.info("END: /addEntry: " + adminEntryResponse.toString());
        return adminEntryResponse;
    }

    @RequestMapping(value = "/queryEntries", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_VALUE)
    public AdminEntry[] queryEntries(){
        AdminEntry[] adminEntries = null;
        try {
            adminEntries = adminService.retrieveEntries();
            //TODO: return a response object for UI to parse
        }catch (VaultException ve){
            ve.printStackTrace();
            LOG.error("VaultException: " + ve.getMessage());
        }
        return adminEntries;
    }
}
