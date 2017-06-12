package com.le.viet.vault.controller;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.model.common.ServiceResponseStatus;
import com.le.viet.vault.model.entry.AdminEntry;
import com.le.viet.vault.model.export.EntryExportResponse;
import com.le.viet.vault.service.ExporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.le.viet.vault.model.common.Common.FAIL;
import static com.le.viet.vault.model.common.Common.SUCCESS;

/**
 * Created by VTL on 6/11/17.
 */
@RestController
@RequestMapping("/rs")
public class DataExporterController {
    private final Logger LOG = LoggerFactory.getLogger(DataExporterController.class);
    @Autowired
    private ExporterService exporterService;

    @RequestMapping(value = "/exportToJSON/{username}", method = RequestMethod.GET,  produces= MediaType.APPLICATION_JSON_VALUE)
    public EntryExportResponse exportToJSON(@PathVariable String username){
        EntryExportResponse entryExportResponse = new EntryExportResponse();
        ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
        try {
            AdminEntry[] entries = exporterService.retrieveEntries(username);
            entryExportResponse.setTotalEntry(entries.length);
            entryExportResponse.setUsername(username);
            entryExportResponse.setAdminEntries(entries);
            serviceResponseStatus.setMessage(SUCCESS);
            serviceResponseStatus.setSuccess(true);
            entryExportResponse.setServiceResponseStatus(serviceResponseStatus);
        } catch (ServiceException se){
            serviceResponseStatus.setMessage(FAIL + ", " + se.getMessage());
            serviceResponseStatus.setSuccess(false);
            entryExportResponse.setServiceResponseStatus(serviceResponseStatus);
        }
        return entryExportResponse;
    }
}
