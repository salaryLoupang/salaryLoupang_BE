package com.project.loupang.service;

import com.project.loupang.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoupangCountService {
    public void loupangCount(UserDetailsImpl userDetails) {
        System.out.println("-----------------");
        System.out.println(userDetails.getUserId());
        System.out.println("-----------------");
        
    }
}
