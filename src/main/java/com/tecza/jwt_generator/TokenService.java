package com.tecza.jwt_generator;

import com.tecza.jwt_generator.model.TokenResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public interface TokenService {

    TokenResponse createResponse(String email, String password, String userAgent, UriComponentsBuilder ucb) throws URISyntaxException, UnknownHostException;
}
