package com.project.loupang.oauth;

import com.project.loupang.domain.OAuthProvider;
import com.project.loupang.response.OAuthInfoResponse;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
