package com.ft.favoritethings.curation.entity;

import com.ft.favoritethings.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "curation")
    private List<Spot> spotList;

    private String title;

    private Boolean isShow;

    @Builder
    public Curation(String title, Boolean isShow, List<Spot> spotList) {
        this.title = title;
        this.isShow = isShow;
        this.spotList = spotList;
    }
}
