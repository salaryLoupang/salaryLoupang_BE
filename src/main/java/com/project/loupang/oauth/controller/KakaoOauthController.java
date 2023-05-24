package com.project.loupang.oauth.controller;

import com.project.loupang.oauth.KakaoLoginParams;
import com.project.loupang.oauth.jwt.AuthTokens;
import com.project.loupang.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KakaoOauthController {


    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/v1/api/oauth")
    public Map<String,String> oauthKakao(@RequestParam String code) {
        HashMap<String, String> result = new HashMap<>();
        result.put("code", code);
        return result;
    }

    @PostMapping("/v1/api/login")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
