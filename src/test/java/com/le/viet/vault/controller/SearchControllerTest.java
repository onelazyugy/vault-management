package com.le.viet.vault.controller;

/**
 * Created by onelazyguy on 3/26/17.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.le.viet.vault.exception.SearchException;
import com.le.viet.vault.model.SearchQuery;
import com.le.viet.vault.model.SearchQueryResponse;
import com.le.viet.vault.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Test
    public void testSearchIsOkStatus() throws Exception{
        String requestJSON = "{\n" +
                "\t\"query\": \"boa|chase\"\t\n" +
                "}";
        String URI = "/rs/search";
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setQuery("boa|chase");

        mockMvc.perform(post(URI).header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchReturnContent() throws Exception{
        SearchQueryResponse[] actual = new SearchQueryResponse[2];

        SearchQueryResponse response = new SearchQueryResponse();
        response.setPassword("password");
        response.setUsername("username");
        response.setTag("tag1|tag2");

        SearchQueryResponse response2 = new SearchQueryResponse();
        response2.setPassword("password2");
        response2.setUsername("myusername2");
        response2.setTag("mytag2");

        actual[0] = response;
        actual[1] = response2;

        String requestJSON = "{\n" +
                "\t\"query\": \"boa|chase\"\t\n" +
                "}";

        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setQuery("boa|chase");

        //given(searchService.search()).willReturn(actual);
        when(searchService.search(searchQuery)).thenReturn(actual);//same as the commented out line above
        String URI = "/rs/search";
        MvcResult mvcResult = mockMvc.perform(post(URI).header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
        String expectedJSON = mvcResult.getResponse().getContentAsString();
        System.out.println("expectedJSON: " + expectedJSON);
        SearchQueryResponse[] expected = new ObjectMapper().readValue(expectedJSON, SearchQueryResponse[].class);
        assertArrayEquals(expected, actual);
        verify(searchService, atLeastOnce()).search(searchQuery);
    }
}
