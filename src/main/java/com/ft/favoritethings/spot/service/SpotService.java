package com.ft.favoritethings.spot.service;

import com.ft.favoritethings.member.entity.Member;
import com.ft.favoritethings.spot.dto.request.SpotRequestDto;
import com.ft.favoritethings.spot.dto.response.ResponseDto;
import com.ft.favoritethings.spot.entity.Spot;
import com.ft.favoritethings.spot.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotService {

    public final SpotRepository spotRepository;

    public ResponseDto<?> createSpot(SpotRequestDto spotRequestDto, Member member) {

        new Spot();
        Spot spot = Spot.builder()
                .title(spotRequestDto.getTitle())
                .description(spotRequestDto.getDescription())
                .member(member)
                .build();

        spotRepository.save(spot);

        return ResponseDto.success("장소 생성");
    }

    public ResponseDto<?> getAllSpots() {
        List<Spot> spots = spotRepository.getAllBy();
        return new ResponseDto<>(200, "Success", spots);
    }

    public ResponseDto<?> showSpecificSpot(Long spotId) {
        Spot spot = spotRepository.getSpotById(spotId);
        return new ResponseDto<>(200, "Success", spot);
    }

    public ResponseDto<?> deleteSpot(Long spotId) {
        spotRepository.deleteById(spotId);
        return new ResponseDto<>(200, "Success", "장소 삭제");
    }
}
