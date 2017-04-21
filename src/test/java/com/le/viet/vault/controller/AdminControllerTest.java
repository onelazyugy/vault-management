package com.le.viet.vault.controller;

import com.le.viet.vault.dao.AdminDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by onelazyguy on 3/26/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdminDaoImpl adminDaoImpl;

    @Test
    public void addEntryTest(){

    }
}
