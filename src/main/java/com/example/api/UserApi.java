package com.example.api;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;

@RestController
@RequestMapping("api/users")
public class UserApi {

    @GetMapping
    User getUser(Principal principal) {
        Authentication auth = (Authentication) principal;
        UserDetails user = (UserDetails) auth.getPrincipal();
        return new User(user.getUsername());
    }
}
