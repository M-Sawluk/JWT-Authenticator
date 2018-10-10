package com.tecza.jwt_generator.controller;

import com.tecza.jwt_generator.TokenService;
import com.tecza.jwt_generator.model.TokenHolder;
import com.tecza.jwt_generator.model.TokenRequest;
import com.tecza.jwt_generator.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping ("/login")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenHolder> generateToken(@RequestBody TokenRequest tokenRequest,
                                                     @RequestHeader("User-Agent") String userAgent, UriComponentsBuilder ucb) {
        TokenResponse response = tokenService.createResponse(tokenRequest.getEmail(), tokenRequest.getPassword(), userAgent, ucb);

        return new ResponseEntity<>(response.getTokenHolder(), response.getHttpHeaders(),  response.getHttpStatus());
    }
}
