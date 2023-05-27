package com.project.loupang.service;

import com.project.loupang.oauth.repository.MemberRepository;
import com.project.loupang.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoupangCountService {
    private final MemberRepository repository;
    public void loupangCount(UserDetailsImpl userDetails){
        System.out.println(userDetails.getNickname()) ;
    }
}
