package com.example.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping
    public String index(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails user = (UserDetails) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());
        return "index";
    }
}
