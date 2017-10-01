package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String error() {
        return "login-error";
    }
}
