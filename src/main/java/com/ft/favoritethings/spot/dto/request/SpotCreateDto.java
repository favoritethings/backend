package com.ft.favoritethings.spot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpotCreateDto {
    private String title;
    private String description;
}
