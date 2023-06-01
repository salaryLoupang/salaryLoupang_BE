package com.project.loupang.oauth.controller;

import com.project.loupang.domain.BackGround;
import com.project.loupang.domain.Member;
import com.project.loupang.oauth.KakaoLoginParams;
import com.project.loupang.oauth.SignupParams;
import com.project.loupang.oauth.jwt.AuthTokens;
import com.project.loupang.oauth.repository.BackGroundRepository;
import com.project.loupang.oauth.repository.MemberRepository;
import com.project.loupang.oauth.service.OAuthLoginService;
import com.project.loupang.request.UrlRequest;
import com.project.loupang.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KakaoOauthController {


    private final OAuthLoginService oAuthLoginService;
    private final BackGroundRepository repository;
    private final MemberRepository memberRepository;

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

    @Operation(summary = "로그인API", description = "회원 정보가 있을 경우 Login ", tags = { "KakaoOauthController" })
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
    public ResponseEntity<AuthTokens> signupKakao(@RequestBody SignupParams params, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(oAuthLoginService.update(params, userDetails));
    }

    @Operation(summary = "이미지 목록 조회 API", description = "이미지 목록 조회", tags = { "KakaoOauthController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/images")
    public List<String> getImage() {
        List<BackGround> all = repository.findAll();
        List<String> response = new ArrayList<>();
        for (BackGround backGround : all) {
            response.add(backGround.getImageUrl());
        }
        return response;
    }

    @Operation(summary = "이미지 등록 API", description = "이미지 등록", tags = { "KakaoOauthController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/images")
    @Transactional
    public ResponseEntity<String> updateImages(@RequestBody UrlRequest imageUrl, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Member member = memberRepository.findById(userDetails.getUserId()).orElse(null);
        member.initImage(imageUrl.getImageNum());
        return ResponseEntity.ok("이미지 저장 완료");
    }

    @Operation(summary = "dummyApi", description = "dummyAPI", tags = { "KakaoOauthController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/url")
    public String insertImage(@RequestBody UrlRequest request) {
        repository.save(new BackGround(request.getImageNum()));
        return "이미지 등록 완료";
    }
}
