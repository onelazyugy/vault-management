package com.le.viet.vault.service;

import com.le.viet.vault.dao.SearchDaoImpl;
import com.le.viet.vault.exception.SearchException;
import com.le.viet.vault.model.SearchQuery;
import com.le.viet.vault.model.SearchQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class SearchService {
    private final Logger LOG = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private SearchDaoImpl searchDaoImpl;


    public SearchQueryResponse[] search(SearchQuery searchQuery) throws SearchException{
        LOG.info("STARTED: search");
        if(searchQuery == null){
            throw new SearchException("search query is null", -1);
        }
        //call Dao here to search based on the query passed in
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
