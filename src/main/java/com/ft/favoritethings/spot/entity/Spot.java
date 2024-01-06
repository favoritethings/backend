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

    private String subTitle;

    @Column(length = 1000)
    private String description;

    private String address;

    private String openingHour;

    private Boolean isParking;

    private String featured_image;

    @ManyToOne
    private Member member;

    @Builder
    public Spot(String title, String subTitle, String description, String address,String openingHour,Boolean isParking,String featured_image,Member member) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.address = address;
        this.openingHour = openingHour;
        this.isParking = isParking;
        this.featured_image = featured_image;
        this.member = member;
    }

}
