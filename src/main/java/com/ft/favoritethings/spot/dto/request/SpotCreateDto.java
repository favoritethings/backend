package com.ft.favoritethings.spot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpotCreateDto {
    @NotBlank(message = "장소명은 필수 입력 값입니다.")
    private String title;
    private String subTitle;
    private String description;
    private String address;
    private String openingHour;
    private Boolean isParking;
    private String featuredImage;
}
