package com.le.viet.vault.controller;

import com.le.viet.vault.dao.AdminDaoImpl;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by onelazyguy on 12/31/16.
 */
@RestController
@RequestMapping("/rs")
public class AdminController {
    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminDaoImpl adminDao;

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addEntry(@RequestBody AdminEntry adminEntry){
        boolean isAddEntrySuccess = false;
        try {
            isAddEntrySuccess = adminDao.add(adminEntry);
        }catch (VaultException ve){
            ve.printStackTrace();
            LOG.error("VaultException: " + ve.getMessage());
        }
        return isAddEntrySuccess;
    }

    @RequestMapping(value = "/queryEntries", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_VALUE)
    public AdminEntry[] queryEntries(){
        AdminEntry[] entires = null;
        try {
            entires = adminDao.retrieveEntries();
        }catch (VaultException ve){
            ve.printStackTrace();
            LOG.error("VaultException: " + ve.getMessage());
        }
        return entires;
    }


}
