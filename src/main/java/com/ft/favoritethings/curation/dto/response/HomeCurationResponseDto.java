package com.ft.favoritethings.curation.dto.response;

import com.ft.favoritethings.spot.dto.response.SpotCurationResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeCurationResponseDto {
    private Long id;

    private String title;

    private List<SpotCurationResponseDto> spotList;

    public HomeCurationResponseDto(String title, List<SpotCurationResponseDto> spotList) {
        this.title = title;
        this.setSpotList(spotList);
    }
}
