package com.company.security.config.jwt;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class JwtConfig {

    @Value("${app.jwt.secretKey}")
    private String secretKey;

    @Value("${app.jwt.tokenExpirationAfterDays}")
    private int tokenExpirationAfterDays;

    @Value("${app.jwt.tokenPrefix}")
    @Getter(AccessLevel.NONE)
    private String tokenPrefix;

    public String getAuthorizationHeader() {
        return "Authorization";
    }

    public String getTokenPrefix() {
        return tokenPrefix + " ";
    }
}
