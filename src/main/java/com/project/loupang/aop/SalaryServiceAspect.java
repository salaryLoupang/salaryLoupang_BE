package com.project.loupang.aop;


import com.project.loupang.security.UserDetailsImpl;
import com.project.loupang.service.LoupangCountService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SalaryServiceAspect {
    private final LoupangCountService service;

    @Before(value = "execution(public * com.project.loupang.controller..*(..))" , argNames = "userDetails")
    public void salaryLoupangCount(UserDetailsImpl userDetails){
        service.loupangCount(userDetails);
        System.out.println("aop test");
    }
}
