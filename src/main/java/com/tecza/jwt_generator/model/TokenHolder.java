package com.tecza.jwt_generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenHolder {
    private final String TOKEN;
    private final String ROLE;
}