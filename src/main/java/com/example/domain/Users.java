package com.example.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users implements Serializable {

    @Id
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
