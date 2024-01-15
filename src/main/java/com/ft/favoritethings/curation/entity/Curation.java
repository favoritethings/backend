package com.ft.favoritethings.curation.entity;

import com.ft.favoritethings.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "CURATION_SPOT",
            joinColumns = @JoinColumn(name = "CURATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPOT_ID")
    )
    private List<Spot> spotList = new ArrayList<>();

    private String title;

    private Boolean isShow;

    public void setSpotList(Spot spot) {
        this.spotList.add(spot);
    }

    public void removeSpot(Spot spot) {
        this.spotList.remove(spot);
    }

    @Builder
    public Curation(String title, Boolean isShow, List<Spot> spotList) {
        this.title = title;
        this.isShow = isShow;
        this.spotList = spotList;
    }
}
