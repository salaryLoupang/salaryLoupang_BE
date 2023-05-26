package com.project.loupang.request;

import com.project.loupang.domain.Travel;
import lombok.Getter;

@Getter
public class TravelRecommendRequest {
    private String title;
    private String description;
    private String imgUrl;

    public Travel toEntity() {
        Travel travel = new Travel(this);
        return travel;
    }
}
