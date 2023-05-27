package com.project.loupang.oauth.repository;

import com.project.loupang.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);

    Optional<Member>  findByNickName(String username);
}
