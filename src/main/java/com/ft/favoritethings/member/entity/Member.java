package com.ft.favoritethings.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String nickname;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String name;
    private String provider;
    @Column(nullable = false)
    private String gender;
    private String age;
    @Column(nullable = false)
    private String password;
    private String profileImage;
    private Date coupleDate;
    private Boolean couple;
    @Enumerated(EnumType.STRING)
    private AccountType account_type;


}
