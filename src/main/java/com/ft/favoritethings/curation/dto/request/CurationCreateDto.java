package com.ft.favoritethings.curation.dto.request;

import com.ft.favoritethings.curation.entity.CurationSection;
import lombok.Getter;

import java.util.List;

@Getter
public class CurationCreateDto {
    private Number orderNo;
    private Boolean isShow;
    private List<CurationSection> curationSections;
}
