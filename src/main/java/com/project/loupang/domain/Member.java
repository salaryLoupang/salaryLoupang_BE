package com.project.loupang.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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

    private String nickName;
    private String career;
    private String jobGroup;
    private String job;
    private int salary;

    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String email, String username, OAuthProvider oAuthProvider, String nickName, String career, String jobGroup, String job, int salary) {
        this.email = email;
        this.username = username;
        this.oAuthProvider = oAuthProvider;
        this.nickName = nickName;
        this.career = career;
        this.jobGroup = jobGroup;
        this.job = job;
        this.salary = salary;
    }
}
