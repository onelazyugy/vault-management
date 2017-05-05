package com.le.viet.vault.service;

import com.le.viet.vault.dao.SearchDao;
import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.search.QueryResponses;
import com.le.viet.vault.model.search.SearchQuery;
import com.le.viet.vault.model.search.SearchQueryResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SearchQueryResponse search(SearchQuery searchQuery) throws ServiceException{
        LOG.info("STARTED: search from SearchService");
        if(searchQuery == null){
            throw new ServiceException("search query is null", SERVICE_EXCEPTION_CD);
        }
        SearchQueryResponse searchQueryResponse = new SearchQueryResponse();
        List<AdminEntry> adminEntryFoundList;
        if(StringUtils.isNotBlank(searchQuery.getQuery())){
            String[] searchTags = searchQuery.getQuery().split("\\|");
            adminEntryFoundList = searchDao.search(searchTags);
            if(adminEntryFoundList != null && adminEntryFoundList.size() > 0){
                QueryResponses[] queryResponseArray = new QueryResponses[adminEntryFoundList.size()];
                for(int i=0; i<adminEntryFoundList.size(); i++){
                    queryResponseArray[i] = getQueryResponses(adminEntryFoundList, i);
                }
                searchQueryResponse.setQueryResponses(queryResponseArray);
            }
        }
        LOG.info("END: search from SearchService");
        return searchQueryResponse;
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
