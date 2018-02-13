package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping
    public String index() {
        return "login";
    }

    @GetMapping(path = "/error")
    public String error() {
        return "login-error";
    }
}
