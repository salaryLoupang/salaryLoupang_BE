package com.project.loupang.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.loupang.domain.OAuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {
    @Schema(description = "카카오 로그인 코드")
    private String authorizationCode;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "닉네임")
    private String nickName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "경력")
    private String career;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "직군")
    private String jobGroup;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "직무")
    private String job;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "연봉")
    private int salary;
    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}

