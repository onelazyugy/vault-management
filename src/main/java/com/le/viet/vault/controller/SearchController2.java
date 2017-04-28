package com.le.viet.vault.controller;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.model.search.SearchQuery;
import com.le.viet.vault.model.search.SearchQueryResponse;
import com.le.viet.vault.service.SearchService;
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
public class SearchController2 {
    private final Logger LOG = LoggerFactory.getLogger(SearchController2.class);
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SearchQueryResponse search(@RequestBody SearchQuery searchQuery){
        SearchQueryResponse responses = null;
        try{
            responses = this.searchService.search(searchQuery);
            //TODO: need a response object back to UI
        } catch (ServiceException se){
            LOG.error("SearchException: " + se.getMessage());
        }
        return responses;
    }
}
