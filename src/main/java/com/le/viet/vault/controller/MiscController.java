package com.le.viet.vault.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by associate on 4/28/17.
 */
@RestController
@RequestMapping("/rs")
public class MiscController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "pong";
    }
}
