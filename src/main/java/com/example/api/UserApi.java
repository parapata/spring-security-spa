package com.example.api;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Users;

@RestController
@RequestMapping("api/users")
public class UserApi {

    @GetMapping
    public Users getUser(Principal principal) {
        Authentication auth = (Authentication) principal;
        UserDetails user = (UserDetails) auth.getPrincipal();
        return new Users(user.getUsername(), user.getPassword(), null, null, null);
    }
}
