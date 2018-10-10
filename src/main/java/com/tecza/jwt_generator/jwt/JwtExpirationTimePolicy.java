package com.tecza.jwt_generator.jwt;

import java.util.HashMap;
import java.util.Map;
import com.tecza.jwt_generator.jwt.constants.*;

public class JwtExpirationTimePolicy {
    private static final Map<String, Integer> TOKEN_EXPIRATION_TIME_POLICY = new HashMap<>();

    static {
        TOKEN_EXPIRATION_TIME_POLICY.put(UserAgents.ANDROID, ExpirationLength.ANDROID_30DAYS);
        TOKEN_EXPIRATION_TIME_POLICY.put(UserAgents.BROWSER, ExpirationLength.BROWSER_15MIN);
    }
    private JwtExpirationTimePolicy() {}

    public static int getExpirationLengthInMinutes(String userAgent) {
        if (userAgent.equalsIgnoreCase(UserAgents.ANDROID)) {
            return TOKEN_EXPIRATION_TIME_POLICY.get(UserAgents.ANDROID);
        } else {
            return TOKEN_EXPIRATION_TIME_POLICY.get(UserAgents.BROWSER);
        }
    }

}
