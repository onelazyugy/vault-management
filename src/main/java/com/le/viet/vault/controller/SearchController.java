package com.le.viet.vault.controller;

import com.le.viet.vault.dao.SearchDaoImpl;
import com.le.viet.vault.exception.SearchException;
import com.le.viet.vault.model.SearchQuery;
import com.le.viet.vault.model.SearchQueryResponse;
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
 * Created by onelazyguy on 3/18/17.
 */
@RestController
@RequestMapping("/rs")
public class SearchController {
    private final Logger LOG = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SearchQueryResponse[] search(@RequestBody SearchQuery searchQuery){
        SearchQueryResponse[] responses = null;
        try{
             responses = this.searchService.search(searchQuery);
        }catch (SearchException se){
            LOG.error("SearchException: " + se.getMessage());
        }
        return responses;
    }
}
