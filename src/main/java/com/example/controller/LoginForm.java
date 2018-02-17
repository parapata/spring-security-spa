package com.example.controller;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginForm implements Serializable {

    private String userId;
    private String password;
}
