package com.project.loupang.oauth.service;

import com.project.loupang.domain.OAuthProvider;
import com.project.loupang.oauth.OAuthApiClient;
import com.project.loupang.oauth.OAuthLoginParams;
import org.springframework.stereotype.Component;
import com.project.loupang.response.OAuthInfoResponse;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream()
                .collect(Collectors.collectingAndThen(
                Collectors.toMap(OAuthApiClient::oAuthProvider, Function.identity()),
                Collections::unmodifiableMap)
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}
