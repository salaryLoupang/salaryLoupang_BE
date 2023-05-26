package com.project.loupang.domain;

import com.project.loupang.request.MenuRecommendRequest;
import com.project.loupang.request.TravelRecommendRequest;
import com.project.loupang.response.MenuRecommendResponse;
import com.project.loupang.response.TravelRecommendResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imgUrl;

    public Travel(TravelRecommendRequest request){
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.imgUrl = request.getImgUrl();
    }

    public TravelRecommendResponse toResponse(){
        return TravelRecommendResponse.from(this);
    }
}
