package com.ft.favoritethings.curation.service;

import com.ft.favoritethings.curation.dto.request.CurationCreateDto;
import com.ft.favoritethings.curation.dto.response.ResponseDto;
import com.ft.favoritethings.curation.entity.Curation;
import com.ft.favoritethings.curation.repository.CurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurationService {

    public final CurationRepository curationRepository;
    public ResponseDto<?> createCuration(CurationCreateDto curationCreateDto) {

        Curation curation = Curation.builder()
                .orderNo(curationCreateDto.getOrderNo())
                .isShow(curationCreateDto.getIsShow())
                .build();

        curationRepository.save(curation);

        return ResponseDto.success("큐레이션 생성");
    }

    public ResponseDto<?> getAllCurations() {
        List<Curation> curations = curationRepository.findAll();

//        return new ResponseDto<>(200, "Success", curations);
        return ResponseDto.success(curations);
    }

    public ResponseDto<?> showCuration(Long id) {
        Optional<Curation> curationOptional = curationRepository.findById(id);

        if(curationOptional.isEmpty()) {
            log.info("큐레이션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation not found", "큐레이션이 존재하지 않습니다.");
        }

        return ResponseDto.success(curationOptional);
    }

    public ResponseDto<?> deleteCuration(Long id) {
        Optional<Curation> curationOptional = curationRepository.findById(id);

        if(curationOptional.isEmpty()) {
            log.info("큐레이션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation not found", "큐레이션이 존재하지 않습니다.");
        }

        curationRepository.deleteById(id);

        return ResponseDto.success("큐레이션 삭제");
    }

    public ResponseDto<?> getHomeCurations() {
        List<Curation> curations = curationRepository.findCurationByIsShowOrderByOrderNo(true);

        return ResponseDto.success(curations);
    }
}
