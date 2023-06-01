package com.project.loupang.oauth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;
    private Long signupFlag;

    public static AuthTokens of(String accessToken, String refreshToken, String grantType, Long expiresIn, Long signupFlag) {
        return new AuthTokens(accessToken, refreshToken, grantType, expiresIn,signupFlag);
    }
}
