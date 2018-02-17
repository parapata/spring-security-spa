package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    public String index(Model model) {
        model.addAttribute(new LoginForm());
        return "login";
    }

    @GetMapping(path = "/login-error")
    public String error(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("loginError", true);
        return "login";
    }
}
