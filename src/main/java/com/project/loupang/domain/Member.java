package com.project.loupang.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String email, String username, OAuthProvider oAuthProvider) {
        this.email = email;
        this.username = username;
        this.oAuthProvider = oAuthProvider;
    }
}
