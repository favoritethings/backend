package com.ft.favoritethings.curation.entity;

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

    private Number orderNo;

    private Boolean isShow;

    @OneToMany(mappedBy = "curation")
    private List<CurationSection> curationSections;

    @Builder
    public Curation(Number orderNo, Boolean isShow) {
        this.orderNo = orderNo;
        this.isShow = isShow;
    }
}
