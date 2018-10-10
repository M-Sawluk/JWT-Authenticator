package com.tecza.jwt_generator.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}