package com.nathan.accountservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role")
    private Set<String> roles;
}
