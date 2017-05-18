package com.le.viet.vault.controller;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.ValidationException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.SearchPromptRequest;
import com.le.viet.vault.model.search.SearchPromptResponse;
import com.le.viet.vault.model.search.SearchQuery;
import com.le.viet.vault.model.search.SearchQueryResponse;
import com.le.viet.vault.service.SearchService;
import com.le.viet.vault.service.UserService;
import com.le.viet.vault.validation.GeneralValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.le.viet.vault.model.common.Common.*;


/**
 * Created by associate on 4/28/17.
 */
@RestController
@RequestMapping("/rs")
public class SearchController {
    private final Logger LOG = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    private SearchService searchService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SearchQueryResponse search(@RequestBody SearchQuery searchQuery, HttpServletRequest request){
        LOG.info("STARTED: /search: " + searchQuery.toString());
        SearchQueryResponse responses = null;
        ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
        try{
            responses = this.searchService.search(searchQuery, request);
            serviceResponseStatus.setMessage(SUCCESS);
            serviceResponseStatus.setSuccess(true);
        } catch (ServiceException se){
            LOG.error("SearchException: " + se.getMessage());
            serviceResponseStatus.setMessage(FAIL + " ["+se.getMessage()+"]");
            serviceResponseStatus.setSuccess(false);
        }
        LOG.info("END: /search");
        responses.setServiceResponseStatus(serviceResponseStatus);
        return responses;
    }

    @RequestMapping(value = "/queryEntryById", method = RequestMethod.POST,  produces= MediaType.APPLICATION_JSON_VALUE)
    public SearchPromptResponse queryEntryById(@RequestBody SearchPromptRequest searchPromptRequest, HttpServletRequest req){
        SearchPromptResponse searchPromptResponse = new SearchPromptResponse();
        ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
        try {
            GeneralValidation.isSearchPromptRequestValid(searchPromptRequest);
            userService.verifyUser(req, searchPromptRequest.getPassword().trim());
            searchPromptResponse = searchService.retrieveEntryById(searchPromptRequest);
            serviceResponseStatus.setSuccess(true);
            serviceResponseStatus.setMessage(SUCCESS);
        } catch (ServiceException se){
            LOG.error("ServiceException: " + se.getMessage());
            serviceResponseStatus.setMessage(FAIL + " ["+se.getMessage()+"]");
            serviceResponseStatus.setSuccess(false);
        } catch (ValidationException ve){
            LOG.error("ServiceException: " + ve.getMessage());
            serviceResponseStatus.setMessage(FAIL + " ["+ve.getMessage()+"]");
            serviceResponseStatus.setSuccess(false);
        }
        searchPromptResponse.setServiceResponseStatus(serviceResponseStatus);
        return searchPromptResponse;
    }
}
