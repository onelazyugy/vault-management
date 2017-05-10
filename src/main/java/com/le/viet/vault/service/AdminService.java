package com.le.viet.vault.service;

import com.le.viet.vault.dao.AdminDao;
import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.le.viet.vault.model.common.Common.*;


/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class AdminService {
    private final Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminDao adminDao;

    public void addEntry(HttpServletRequest req, AdminEntry adminEntry) throws ServiceException{
        LOG.info("STARTED: addEntry");
        try {
            HttpSession session = req.getSession(false);
            if(session != null) {
                String currentUser = (String) session.getAttribute("currentLoggedInUser");
                adminEntry.setDateTime(new Date().toString());
                adminEntry.setMasterPassword(Utils.hash(adminEntry.getMasterPassword().trim()));
                adminEntry.setMasterUsername(currentUser);
                //TODO: encrypt the password using the master password as key
                adminDao.addEntry(adminEntry);
            } else {
                throw new ServiceException("session is not available, please login again", DATA_EXCEPTION);
            }
        } catch (DaoException de){
            LOG.error("DaoException: " + de.toString());
            throw new ServiceException(de.getMessage(), de.getStatusCd());
        } catch (VaultException ve){
            LOG.error("VaultException: " + ve.getMessage());
            throw new ServiceException(ve.getMessage(), VALIDATION_EXCEPTION);
        } catch (Exception e){
            LOG.error("Exception: " + e.getMessage());
            throw new ServiceException(e.getMessage(), GENERAL_EXCEPTION_CD);
        }
        LOG.info("END: addEntry");
    }

    public AdminEntry[] retrieveEntries(HttpServletRequest req) throws ServiceException{
        AdminEntry[] adminEntries;
        try{
            HttpSession session = req.getSession(false);
            if(session != null) {
                String currentUser = (String) session.getAttribute("currentLoggedInUser");
                if(StringUtils.isBlank(currentUser)){
                    throw new ServiceException("session is not available, please login again", DATA_EXCEPTION);
                }
                adminEntries = adminDao.retrieveEntries(currentUser.trim());
                if(adminEntries == null || adminEntries.length <= 0){
                    throw new ServiceException("zero entry retrieved", DATA_EXCEPTION);
                }
                adminEntries = adminDao.retrieveEntries(currentUser.trim());
            } else {
                throw new ServiceException("session is not available, please login again", DATA_EXCEPTION);
            }
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
