package com.le.viet.vault.service;

import com.le.viet.vault.dao.SearchDao;
import com.le.viet.vault.exception.DaoException;
import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.*;
import com.le.viet.vault.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.le.viet.vault.model.common.Common.*;

/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class SearchService {
    private final Logger LOG = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private SearchDao searchDao;

    public SearchQueryResponse search(SearchQuery searchQuery, HttpServletRequest req) throws ServiceException{
        LOG.info("STARTED: search from SearchService");
        SearchQueryResponse searchQueryResponse = new SearchQueryResponse();
        HttpSession session = req.getSession(false);
        if(session != null) {
            String currentUser = (String) session.getAttribute("currentLoggedInUser");
            if(StringUtils.isBlank(currentUser)){
                throw new ServiceException("session is not available, please login again", DATA_EXCEPTION);
            }
            if(searchQuery == null){
                throw new ServiceException("search query is null", SERVICE_EXCEPTION_CD);
            }
            List<AdminEntry> adminEntryFoundList;
            if(StringUtils.isNotBlank(searchQuery.getQuery())){
                String[] searchTags = searchQuery.getQuery().split("\\|");
                adminEntryFoundList = searchDao.search(searchTags, currentUser.trim());
                if(adminEntryFoundList != null && adminEntryFoundList.size() > 0){
                    QueryResponses[] queryResponseArray = new QueryResponses[adminEntryFoundList.size()];
                    for(int i=0; i<adminEntryFoundList.size(); i++){
                        queryResponseArray[i] = getQueryResponses(adminEntryFoundList, i);
                    }
                    searchQueryResponse.setQueryResponses(queryResponseArray);
                }
            }
            LOG.info("END: search from SearchService");
        } else {
            throw new ServiceException("session is not available, please login again", DATA_EXCEPTION);
        }
        return searchQueryResponse;
    }

    public SearchPromptResponse retrieveEntryById(SearchPromptRequest searchPromptRequest) throws ServiceException{
        SearchPromptResponse searchPromptResponse = new SearchPromptResponse();
        try {
            AdminEntry entry = searchDao.retrieveEntry(searchPromptRequest.getId().trim());
            if(entry != null){
                String entryMasterPassword = entry.getMasterPassword();
                String hashedPassword = Utils.hash(searchPromptRequest.getPassword().trim());
                if(entryMasterPassword.equals(hashedPassword)){
                    //TODO: use the unhased password from UI to decrypt entry.getPassword()
                    searchPromptResponse.setPassword(entry.getPassword().trim());
                } else {
                    throw new ServiceException("master password provided is incorrect", DATA_EXCEPTION);
                }
            } else {
                throw new ServiceException("entry does not exist for give id", DATA_EXCEPTION);
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
        return searchPromptResponse;
    }

    public QueryResponses getQueryResponses(List<AdminEntry> adminEntryFoundList, int index) {
        QueryResponses queryResponse = new QueryResponses();
        AdminEntry adminEntry = adminEntryFoundList.get(index);
        queryResponse.setPassword(adminEntry.getPassword().trim());
        queryResponse.setTag(adminEntry.getTag().trim());
        queryResponse.setUsername(adminEntry.getUsername().trim());
        queryResponse.setId(adminEntry.getId().trim());
        return queryResponse;
    }
}
