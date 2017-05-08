package com.le.viet.vault.controller;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.VaultException;
import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.SearchPromptRequest;
import com.le.viet.vault.model.search.SearchPromptResponse;
import com.le.viet.vault.model.search.SearchQuery;
import com.le.viet.vault.model.search.SearchQueryResponse;
import com.le.viet.vault.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SearchQueryResponse search(@RequestBody SearchQuery searchQuery){
        LOG.info("STARTED: /search: " + searchQuery.toString());
        SearchQueryResponse responses = null;
        ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
        try{
            responses = this.searchService.search(searchQuery);
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
    public SearchPromptResponse queryEntryById(@RequestBody SearchPromptRequest searchPromptRequest){
        SearchPromptResponse searchPromptResponse = new SearchPromptResponse();
        ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
        try {
            searchPromptResponse = searchService.retrieveEntryById(searchPromptRequest.getId());
            serviceResponseStatus.setSuccess(true);
            serviceResponseStatus.setMessage(SUCCESS);
        } catch (ServiceException se){
            LOG.error("ServiceException: " + se.getMessage());
            serviceResponseStatus.setMessage(FAIL + " ["+se.getMessage()+"]");
            serviceResponseStatus.setSuccess(false);
        }
        searchPromptResponse.setServiceResponseStatus(serviceResponseStatus);
        return searchPromptResponse;
    }
}
