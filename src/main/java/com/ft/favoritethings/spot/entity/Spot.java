package com.ft.favoritethings.spot.entity;

import com.ft.favoritethings.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne // Many = Spot, Member =One 한명의 유저가 여러 장소를 생성할 수 있다.
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Spot(String title, String description, Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

}
