package com.project.loupang.response;

import com.project.loupang.domain.Menu;
import com.project.loupang.domain.Travel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class TravelRecommendResponse {
    @Schema(description = "여행지추천 제목")
    private String title;
    @Schema(description = "여행지추천 image Url")
    private String imgUrl;
    @Schema(description = "여행지추천 설명")
    private String description;

    public static TravelRecommendResponse from(Travel travel) {
        TravelRecommendResponse response = new TravelRecommendResponse();
        response.initResponse(travel);
        return response;
    }

    public void initResponse(Travel travel){
        this.title = travel.getTitle();
        this.imgUrl = travel.getImgUrl();
        this.description = travel.getDescription();
    }
}
