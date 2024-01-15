package com.ft.favoritethings.tag.entity;

import com.ft.favoritethings.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "tagList")
    @JsonIgnore
    private List<Spot> spotList = new ArrayList<>();

    private String name;

    @Builder
    public Tag(String name) {
        this.name = name;
    }
}
