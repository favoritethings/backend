package com.ft.favoritethings.spot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ft.favoritethings.curation.entity.Curation;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURATION_ID")
    @JsonIgnore
    private Curation curation;

    @Column(nullable = false)
    private String title;

    private String subTitle;

    @Column(length = 1000)
    private String description;

    private String address;

    private String openingHour;

    private Boolean isParking;

    private String featured_image;

    private String curation_image;

    @Builder
    public Spot(Curation curation, String title, String subTitle, String description, String address,String openingHour, Boolean isParking, String featured_image) {
        this.curation = curation;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.address = address;
        this.openingHour = openingHour;
        this.isParking = isParking;
        this.featured_image = featured_image;
    }

}
