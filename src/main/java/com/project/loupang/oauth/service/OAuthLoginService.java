package com.project.loupang.oauth.service;

import com.project.loupang.domain.Member;
import com.project.loupang.oauth.OAuthLoginParams;
import com.project.loupang.oauth.SignupParams;
import com.project.loupang.oauth.jwt.AuthTokens;
import com.project.loupang.oauth.jwt.AuthTokensGenerator;
import com.project.loupang.oauth.repository.MemberRepository;
import com.project.loupang.response.OAuthInfoResponse;
import com.project.loupang.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Member member = memberRepository.findByEmail(oAuthInfoResponse.getEmail()).orElse(null);
        if(member == null){
            newMember(oAuthInfoResponse,params);
            return 0L;
        }
        return member.getId();
    }

    @Transactional
    public AuthTokens update(SignupParams params, UserDetailsImpl userDetails) {
        Member member = memberRepository.findById(userDetails.getUserId()).orElse(null);
        Long memberId = updateMember(member, params);
        return authTokensGenerator.generate(memberId, member.getUsername());
    }
    public Long updateMember(Member member, SignupParams params) {
        member.updateMember(params);
        return member.getId();
    }

    private String findMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getEmail)
                .orElseGet(() -> null);
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse, OAuthLoginParams params) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .username(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}
