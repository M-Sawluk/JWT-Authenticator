package com.tecza.jwt_generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class TokenResponse {
    private final TokenHolder tokenHolder;
    private final HttpHeaders httpHeaders;
    private final HttpStatus httpStatus;
}
