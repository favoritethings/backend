package com.ft.favoritethings.curation.dto.request;

import com.ft.favoritethings.curation.entity.Curation;
import com.ft.favoritethings.spot.entity.Spot;
import lombok.Getter;

@Getter
public class CurationSectionCreateDto {
    private Curation curation;
    private Spot spot;
    private Number orderNo;
    private Boolean isShow;
    private String featuredImage;
}
