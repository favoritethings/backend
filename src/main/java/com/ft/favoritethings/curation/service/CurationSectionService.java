package com.ft.favoritethings.curation.service;

import com.ft.favoritethings.curation.dto.request.CurationSectionCreateDto;
import com.ft.favoritethings.curation.dto.response.ResponseDto;
import com.ft.favoritethings.curation.entity.CurationSection;
import com.ft.favoritethings.curation.repository.CurationSectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurationSectionService {

    public final CurationSectionRepository curationSectionRepository;

    public ResponseDto<?> createCurationSection(CurationSectionCreateDto curationSectionCreateDto) {

        CurationSection curationSection = CurationSection.builder()
                .curation(curationSectionCreateDto.getCuration())
                .spot(curationSectionCreateDto.getSpot())
                .orderNo(curationSectionCreateDto.getOrderNo())
                .isShow(curationSectionCreateDto.getIsShow())
                .featured_image(curationSectionCreateDto.getFeaturedImage())
                .build();

        curationSectionRepository.save(curationSection);

        return ResponseDto.success(curationSectionCreateDto.getCuration());
    }


    public ResponseDto<?> getAllCurationSections() {
        List<CurationSection> curationSections = curationSectionRepository.findAll();

        return ResponseDto.success(curationSections);
    }

    public ResponseDto<?> showCurationSection(Long id) {
        Optional<CurationSection> curationSectionOptional = curationSectionRepository.findById(id);

        if(curationSectionOptional.isEmpty()) {
            log.info("큐레이션 섹션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation Section not found", "큐레이션 섹션이 존재하지 않습니다.");
        }

        return ResponseDto.success(curationSectionOptional);
    }

    public ResponseDto<?> deleteCurationSection(Long id) {
        Optional<CurationSection> curationSectionOptional = curationSectionRepository.findById(id);

        if(curationSectionOptional.isEmpty()) {
            log.info("큐레이션 섹션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation Section not found", "큐레이션 섹션이 존재하지 않습니다.");
        }

        curationSectionRepository.deleteById(id);

        return ResponseDto.success("큐레이션 섹션 삭제");
    }
}
