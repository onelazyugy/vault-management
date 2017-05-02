package com.le.viet.vault.service;

import com.le.viet.vault.dao.AdminDao;
import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.model.entry.AdminEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.le.viet.vault.model.common.Common.*;


/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class AdminService {
    private final Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminDao adminDao;

    public void addEntry(AdminEntry adminEntry) throws ServiceException{
        LOG.info("STARTED: addEntry");
        try {
            adminDao.addEntry(adminEntry);
        } catch (DaoException de){
            LOG.error("DaoException: " + de.toString());
            throw new ServiceException(de.getMessage(), de.getStatusCd());
        } catch (Exception e){
            LOG.error("Exception: " + e.getMessage());
            throw new ServiceException(e.getMessage(), GENERAL_EXCEPTION_CD);
        }
        LOG.info("END: addEntry");
    }

    public AdminEntry[] retrieveEntries() throws ServiceException{
        AdminEntry[] adminEntries;
        try{
            adminEntries = adminDao.retrieveEntries();
        } catch (DaoException de){
            LOG.error("DaoException: " + de.toString());
            throw new ServiceException(de.getMessage(), de.getStatusCd());
        } catch (Exception e){
            LOG.error("Exception: " + e.getMessage());
            throw new ServiceException(e.getMessage(), GENERAL_EXCEPTION_CD);
        }
        return adminEntries;
    }
}
