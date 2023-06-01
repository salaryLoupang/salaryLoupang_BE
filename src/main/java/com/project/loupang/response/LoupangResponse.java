package com.project.loupang.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoupangResponse {
    @Schema(description = "유저 ID")
    private Long userId;
    @Schema(description = "유저 닉네임")
    private String userNickName;
    @Schema(description = "루팡 시간")
    private long loupangTime;
    @Schema(description = "루팡한 연봉")
    private long loupangSalary;
    @Schema(description = "선택한 이미지")
    private String imageNum;
}
