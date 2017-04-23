package com.le.viet.vault.service;

import com.le.viet.vault.dao.SearchDaoImpl;
import com.le.viet.vault.exception.SearchException;
import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.search.QueryResponses;
import com.le.viet.vault.model.search.SearchQuery;
import com.le.viet.vault.model.search.SearchQueryResponse;
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


    public SearchQueryResponse search(SearchQuery searchQuery) throws SearchException{
        LOG.info("STARTED: search");
        if(searchQuery == null){
            throw new SearchException("search query is null", -1);
        }
        //call Dao here to search based on the query passed in

        //
        SearchQueryResponse searchQueryResponse = new SearchQueryResponse();
        QueryResponses[] queryResponses = new QueryResponses[2];
        QueryResponses qr1 = new QueryResponses();
        qr1.setUsername("test_user");
        qr1.setPassword("123_password");
        qr1.setTag("some|tag|here");

        QueryResponses qr2 = new QueryResponses();
        qr2.setUsername("some_user");
        qr2.setPassword("123_password");
        qr2.setTag("some|tag|here");
        //

        queryResponses[0] = qr1;
        queryResponses[1] = qr2;

        ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
        serviceResponseStatus.setMessage("success");
        serviceResponseStatus.setSuccess(true);

        searchQueryResponse.setQueryResponses(queryResponses);
        searchQueryResponse.setServiceResponseStatus(serviceResponseStatus);

        return searchQueryResponse;
    }
}
