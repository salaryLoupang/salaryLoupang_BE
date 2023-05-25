package com.project.loupang.request;

import com.project.loupang.domain.Menu;
import lombok.Getter;

@Getter
public class MenuRecommendRequest {
    private String title;
    private String description;
    private String imgUrl;

    public Menu toEntity() {
        Menu menu = new Menu(this);
        return menu;
    }
}
