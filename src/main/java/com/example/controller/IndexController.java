package com.example.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails user = (UserDetails) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());
        return "index";
    }
}
