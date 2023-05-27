package com.project.loupang.oauth.controller;

import com.project.loupang.oauth.KakaoLoginParams;
import com.project.loupang.oauth.jwt.AuthTokens;
import com.project.loupang.oauth.service.OAuthLoginService;
import com.project.loupang.oauth.service.SignupParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KakaoOauthController {


    private final OAuthLoginService oAuthLoginService;

    @Operation(summary = "카카오 로그인 코드 return", description = "카카오 로그인 코드 return", tags = { "KakaoOauthController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/oauth")
    public Map<String,String> oauthKakao(@RequestParam String code) {
        HashMap<String, String> result = new HashMap<>();
        result.put("code", code);
        return result;
    }

    @Operation(summary = "로그인 API", description = "회원 정보가 있을 경우 Login", tags = { "KakaoOauthController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/login")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @Operation(summary = "회원가입 API", description = "회원가입", tags = { "KakaoOauthController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/signup")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody SignupParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
