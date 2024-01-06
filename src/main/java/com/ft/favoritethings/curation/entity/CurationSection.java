package com.ft.favoritethings.curation.entity;

import com.ft.favoritethings.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurationSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Curation curation;

    @ManyToOne(optional = false)
    private Spot spot;

    private Number orderNo;

    private Boolean isShow;

    private String featured_image;

    @Builder
    public CurationSection(Curation curation, Spot spot, Number orderNo, Boolean isShow, String featured_image) {
        this.curation = curation;
        this.spot = spot;
        this.orderNo = orderNo;
        this.isShow = isShow;
        this.featured_image = featured_image;
    }


}
