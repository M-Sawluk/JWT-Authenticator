package com.tecza.jwt_generator.service;

import com.tecza.jwt_generator.TokenService;
import com.tecza.jwt_generator.entity.User;
import com.tecza.jwt_generator.jwt.JwtUtils;
import com.tecza.jwt_generator.model.TokenHolder;
import com.tecza.jwt_generator.model.TokenResponse;
import com.tecza.jwt_generator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.*;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenResponse createResponse(String email, String password, String userAgent, UriComponentsBuilder ucb) throws URISyntaxException, UnknownHostException {
        User user = userRepository.findByEmail(email);
        HttpHeaders httpHeaders = new HttpHeaders();
        boolean passwordCorrect = isPasswordCorrect(user, password);

        if(!passwordCorrect) {
            httpHeaders.setLocation(ucb.path("/accessDenied").build().toUri());
            httpHeaders.set("message", "Zły email lub hasło");
            return new TokenResponse(new TokenHolder(""), httpHeaders, HttpStatus.NOT_FOUND);
        }

        String roleName = user.getRole().getRoleName();
        httpHeaders.setLocation(new URI("http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/tecza/" + roleName));

        String token = JwtUtils.createToken(user, userAgent);

        return new TokenResponse(new TokenHolder(token), httpHeaders, HttpStatus.OK);
    }

    private boolean isPasswordCorrect(User user, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
        return user != null && bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}
