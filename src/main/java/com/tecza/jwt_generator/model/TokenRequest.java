package com.tecza.jwt_generator.model;

import lombok.Data;

@Data
public class TokenRequest {
    private String email;
    private String password;
}
