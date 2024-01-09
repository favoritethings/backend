package com.ft.favoritethings.curation.dto.request;

import com.ft.favoritethings.spot.entity.Spot;
import lombok.Getter;

import java.util.List;

@Getter
public class CurationCreateDto {
    private String title;
    private Boolean isShow;
    private List<Spot> spotList;
}
