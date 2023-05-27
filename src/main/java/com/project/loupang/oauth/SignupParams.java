package com.project.loupang.oauth;

import com.project.loupang.domain.OAuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Getter
@NoArgsConstructor
public class SignupParams {
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "경력")
    private String career;
    @Schema(description = "직군")
    private String jobGroup;
    @Schema(description = "직무")
    private String job;
    @Schema(description = "연봉")
    private int salary;

}

