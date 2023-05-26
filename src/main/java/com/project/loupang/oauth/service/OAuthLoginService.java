package com.project.loupang.oauth.service;

import com.project.loupang.domain.Member;
import com.project.loupang.oauth.OAuthLoginParams;
import com.project.loupang.oauth.jwt.AuthTokens;
import com.project.loupang.oauth.jwt.AuthTokensGenerator;
import com.project.loupang.oauth.repository.MemberRepository;
import com.project.loupang.response.OAuthInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse, params);
        String username = findMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId, username);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse, OAuthLoginParams params) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse, params));
    }

    private String findMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getNickName)
                .orElseGet(() -> null);
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse, OAuthLoginParams params) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .username(oAuthInfoResponse.getNickname())
                .nickName(params.getNickName())
                .job(params.getJob())
                .career(params.getCareer())
                .jobGroup(params.getJobGroup())
                .salary(params.getSalary())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}
