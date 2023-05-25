package com.project.loupang.response;

import com.project.loupang.domain.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MenuRecommendResponse {
    @Schema(description = "메뉴추천 제목")
    private String title;
    @Schema(description = "메뉴추천 image Url")
    private String imgUrl;
    @Schema(description = "메뉴추천 설명")
    private String description;

    public static MenuRecommendResponse from(Menu menu) {
        MenuRecommendResponse response = new MenuRecommendResponse();
        response.initResponse(menu);
        return response;
    }

    public void initResponse(Menu menu){
        this.title = menu.getTitle();
        this.imgUrl = menu.getImgUrl();
        this.description = menu.getDescription();
    }
}
