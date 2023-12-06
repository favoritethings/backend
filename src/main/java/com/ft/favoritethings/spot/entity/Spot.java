package com.ft.favoritethings.spot.entity;

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

    private Long user_id;

    @Builder
    public Spot(String title, String description, Long user_id) {
        this.title = title;
        this.description = description;
        this.user_id = user_id;
    }

}
