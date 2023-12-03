package com.ft.favoritethings.member.repository;


import com.ft.favoritethings.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    Optional<Member> findByEmail(String email);
}
