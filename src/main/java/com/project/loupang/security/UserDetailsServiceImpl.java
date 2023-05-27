package com.project.loupang.security;


import com.project.loupang.domain.Member;
import com.project.loupang.oauth.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository userRepository) {
        this.userRepository = userRepository;
    }

    //UserDetailsService가 가지고 있는 함수중 loadUserByUsername 를 사용
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = userRepository.findByEmail(username)

                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(user);
    }
}

