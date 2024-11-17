package com.nathan.accountservice.model;

import lombok.Data;

import java.util.Set;

@Data
public class AccountDTO {

    private Long id;
    private String name;
    private String username;
    private String password;
    private Set<String> roles;

}
