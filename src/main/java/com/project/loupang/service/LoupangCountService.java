package com.project.loupang.service;

import com.project.loupang.domain.Member;
import com.project.loupang.oauth.repository.MemberRepository;
import com.project.loupang.response.LoupangResponse;
import com.project.loupang.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LoupangCountService {

    private final MemberRepository memberRepository;

    @Transactional
    public void loupangCount(UserDetailsImpl userDetails) {
        Member member = memberRepository.findById(userDetails.getUserId()).orElse(null);
        String startTime = member.getStartTime();

        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
        Date currentTime = new Date ();
        String mTime = format.format ( currentTime );

        long diff = 0L;
        try {
            Date d1 = format.parse(startTime);
            Date d2 = format.parse(mTime);
            diff = d2.getTime() - d1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diffSeconds = diff / 1000;

        if(diffSeconds >= 1800){
            diffSeconds = 0L;
        }
        long loupangTime = member.getLoupangTime() + diffSeconds;
        member.initLoupangTime(mTime, loupangTime);

    }

    public LoupangResponse findLoupang(UserDetailsImpl userDetails) {
        Member member = memberRepository.findById(userDetails.getUserId()).orElse(null);
        int salary = member.getSalary();
        long loupangTime = member.getLoupangTime();
        int month = (salary*10000) / 12;
        int hour = month / 209;
        int sec = hour / 3600;
        long loupangMoney = sec * loupangTime;

        return new LoupangResponse(member.getId(), member.getNickName(), member.getLoupangTime(),loupangMoney,member.getImageUrl());
    }
}
