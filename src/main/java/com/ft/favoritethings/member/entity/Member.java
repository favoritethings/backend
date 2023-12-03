package com.ft.favoritethings.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Column(unique = true, nullable = false, name = "phone")
    private String phone;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    @Builder
    public Member(String email, String nickname, String phone, String password) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.account_type = AccountType.ROLE_USER;
    }

}
