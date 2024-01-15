package com.ft.favoritethings.curation.service;

import com.ft.favoritethings.curation.dto.request.CurationCreateDto;
import com.ft.favoritethings.common.dto.response.ResponseDto;
import com.ft.favoritethings.curation.entity.Curation;
import com.ft.favoritethings.curation.repository.CurationRepository;
import com.ft.favoritethings.spot.entity.Spot;
import com.ft.favoritethings.spot.repository.SpotRepository;
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
    public final SpotRepository spotRepository;

    public ResponseDto<?> createCuration(CurationCreateDto curationCreateDto) {

        Curation curation = Curation.builder()
                .title(curationCreateDto.getTitle())
                .isShow(curationCreateDto.getIsShow())
                .spotList(curationCreateDto.getSpotList())
                .build();

        curationRepository.save(curation);

        return ResponseDto.success("큐레이션 생성");
    }

    public ResponseDto<?> getAllCurations() {
        List<Curation> curations = curationRepository.findAll();

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
        List<Curation> curations = curationRepository.findCurationByIsShow(true);

        return ResponseDto.success(curations);
    }

    public ResponseDto<?> postCurationSpot(Long curationId, Long spotId) {

        Optional<Curation> curationOptional = curationRepository.findById(curationId);

        if(curationOptional.isEmpty()) {
            log.info("큐레이션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation not found", "큐레이션이 존재하지 않습니다.");
        }

        Optional<Spot> spotOptional = spotRepository.findById(spotId);

        if(spotOptional.isEmpty()) {
            log.info("장소가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Spot not found", "장소가 존재하지 않습니다.");
        }

        Spot spot = spotOptional.get();
        Curation curation = curationOptional.get();
        curation.setSpotList(spot);

        curationRepository.save(curation);

        return ResponseDto.success(curation);
    }

    public ResponseDto<?> deleteCurationSpot(Long curationId, Long spotId) {

        Optional<Curation> curationOptional = curationRepository.findById(curationId);

        if(curationOptional.isEmpty()) {
            log.info("큐레이션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation not found", "큐레이션이 존재하지 않습니다.");
        }

        Optional<Spot> spotOptional = spotRepository.findById(spotId);

        if(spotOptional.isEmpty()) {
            log.info("장소가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Spot not found", "장소가 존재하지 않습니다.");
        }

        Spot spot = spotOptional.get();
        Curation curation = curationOptional.get();
        curation.removeSpot(spot);

        curationRepository.save(curation);

        return ResponseDto.success(curation);
    }

}
