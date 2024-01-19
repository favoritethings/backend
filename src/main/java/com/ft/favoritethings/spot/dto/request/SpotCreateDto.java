package com.ft.favoritethings.spot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpotCreateDto {
    private String title;
    private String subTitle;
    private String description;
    private String address;
    private String openingHour;
    private Boolean isParking;
    private String featuredImage;
    private String curationImage;
}
