package com.ft.favoritethings.spot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ft.favoritethings.curation.entity.Curation;
import com.ft.favoritethings.member.entity.Member;
import com.ft.favoritethings.tag.entity.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToMany(mappedBy = "spotList")
    @JsonIgnore
    private List<Curation> curationList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "SPOT_TAG",
            joinColumns = @JoinColumn(name = "SPOT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private List<Tag> tagList = new ArrayList<>();

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

    public void setTagList(Tag tag) {
        this.tagList.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tagList.remove(tag);
    }

    @Builder
    public Spot(Member member, List<Curation> curationList, List<Tag> tagList, String title, String subTitle, String description, String address, String openingHour, Boolean isParking, String featured_image) {
        this.member = member;
        this.curationList = curationList;
        this.tagList = tagList;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.address = address;
        this.openingHour = openingHour;
        this.isParking = isParking;
        this.featured_image = featured_image;
    }
}
