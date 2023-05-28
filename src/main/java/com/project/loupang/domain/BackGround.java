package com.project.loupang.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class BackGround {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    public BackGround(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BackGround() {

    }
}
