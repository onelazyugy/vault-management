package com.le.viet.vault.service;

import com.le.viet.vault.dao.AdminDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by onelazyguy on 4/21/17.
 */
@Service
public class AdminService {
    @Autowired
    private AdminDaoImpl adminDao;

    private final Logger LOG = LoggerFactory.getLogger(AdminService.class);
}
