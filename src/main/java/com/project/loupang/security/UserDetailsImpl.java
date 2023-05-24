package com.project.loupang.security;


import com.project.loupang.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private Member user;

    private Long userId;
    private String nickname;


    public UserDetailsImpl(Member user) {
        this.user = user;
    }

    public Member getUser() {
        return user;
    }


    public String getNickname() {
        return user.getUsername();
    }

    public Long getUserId() {
        return user.getId();
    }


    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }


    // User로부터 UserDetailsImpl 생성
    public static UserDetailsImpl fromUser(Member user){

        UserDetailsImpl userDetails = new UserDetailsImpl();

        userDetails.userId = user.getId();
        userDetails.nickname = user.getUsername();

        return userDetails;
    }

    // 비어있는 UserDetailsImpl 생성
    public static UserDetailsImpl createEmpty(){

        UserDetailsImpl userDetails = new UserDetailsImpl();

        userDetails.userId = null;
        userDetails.nickname = null;

        return userDetails;
    }

}