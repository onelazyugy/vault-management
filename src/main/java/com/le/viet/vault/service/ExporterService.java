package com.le.viet.vault.service;

import com.le.viet.vault.dao.AdminDao;
import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.model.entry.AdminEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.le.viet.vault.model.common.Common.DATA_EXCEPTION;
import static com.le.viet.vault.model.common.Common.GENERAL_EXCEPTION_CD;

/**
 * Created by VTL on 6/11/17.
 */
@Service
public class ExporterService{
    private final Logger LOG = LoggerFactory.getLogger(ExporterService.class);
    @Autowired
    private AdminDao adminDao;

    public AdminEntry[] retrieveEntries(String username) throws ServiceException {
        AdminEntry[] entries;
        try {
            entries = adminDao.retrieveEntries(username.trim());
        } catch (DaoException de){
            LOG.error("Unable to retrieve all entry, [" +de.getMessage()+ "]");
            throw new ServiceException(de.getMessage(), DATA_EXCEPTION);
        } catch (Exception e){
            LOG.error("Exception, [" +e.getMessage()+ "]");
            throw new ServiceException(e.getMessage(), GENERAL_EXCEPTION_CD);
        }
        return entries;
    }
}
