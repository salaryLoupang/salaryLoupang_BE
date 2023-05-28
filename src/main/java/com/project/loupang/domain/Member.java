package com.project.loupang.domain;

import com.project.loupang.oauth.SignupParams;
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

    private String startTime;
    private long loupangTime;
    private String imageUrl;

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

    public void updateMember(SignupParams params, String mTime) {
        this.nickName = params.getNickName();
        this.career = params.getCareer();
        this.jobGroup = params.getJobGroup();
        this.job = params.getJob();
        this.salary = params.getSalary();
        this.startTime = mTime;
        this.loupangTime = 0L;
    }

    public void initLoupangTime(String startTime, long diffSeconds) {
        this.startTime = startTime;
        this.loupangTime = diffSeconds;
    }

    public void initImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
