package com.le.viet.vault.controller;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by associate on 4/28/17.
 */
@RestController
@RequestMapping("/rs")
public class AdminController2 {
    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEntry(@RequestBody AdminEntry adminEntry){
        try{
            adminService.addEntry(adminEntry);
        } catch (ServiceException se){
            //TODO: return a response object for UI to parse
            se.printStackTrace();
            LOG.error("ServiceException: " + se.getMessage());
        }
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
