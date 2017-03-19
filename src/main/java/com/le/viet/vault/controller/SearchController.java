package com.le.viet.vault.controller;

import com.le.viet.vault.dao.SearchDao;
import com.le.viet.vault.model.SearchQuery;
import com.le.viet.vault.model.SearchQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SearchDao searchDao;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchQueryResponse[] getUserStatus(@RequestBody SearchQuery search){
        SearchQueryResponse[] responses = new SearchQueryResponse[2];

        SearchQueryResponse searchQueryResponse = new SearchQueryResponse();
        searchQueryResponse.setUsername("test_user");
        searchQueryResponse.setPassword("123_password");
        searchQueryResponse.setTag("some|tag|here");

        SearchQueryResponse searchQueryResponse1 = new SearchQueryResponse();
        searchQueryResponse1.setUsername("some_user");
        searchQueryResponse1.setPassword("123_password");
        searchQueryResponse1.setTag("some|tag|here");

        responses[0] = searchQueryResponse;
        responses[1] = searchQueryResponse1;

        return responses;
    }
}
