package com.ft.favoritethings.spot.service;

import com.ft.favoritethings.member.entity.AccountType;
import com.ft.favoritethings.member.entity.Member;
import com.ft.favoritethings.spot.dto.request.SpotCreateDto;
import com.ft.favoritethings.spot.dto.response.ResponseDto;
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
public class SpotService {

    public final SpotRepository spotRepository;

    public ResponseDto<?> createSpot(SpotCreateDto spotCreateDto, Member member) {

        new Spot();
        Spot spot = Spot.builder()
                .title(spotCreateDto.getTitle())
                .description(spotCreateDto.getDescription())
                .member(member)
                .build();

        spotRepository.save(spot);

        return ResponseDto.success("장소 생성");
    }

    public ResponseDto<?> getAllSpots() {

        List<Spot> spots = spotRepository.findAll();
        return new ResponseDto<>(200, "Success", spots);
    }

    public ResponseDto<?> showSpecificSpot(Long spotId) {

        Optional<Spot> spotOptional = spotRepository.findById(spotId);

        if (spotOptional.isPresent()) {
            return ResponseDto.success(spotOptional);
        } else {
            log.info("장소가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Spot not found", "장소가 존재하지 않습니다.");
        }
    }

    public ResponseDto<?> deleteSpot(Long spotId, Member member) {

        if(member == null) {
            log.info("유저 정보가 없습니다.");
            return ResponseDto.fail(404, "Member is Null",  "유저 정보를 확인할 수 없습니다.");
        }

        if (!AccountType.ROLE_ADMIN.equals(member.getAccount_type())) {
            log.info("삭제 권한이 없는 유저입니다.");
            return ResponseDto.fail(404, "Member is not admin",  "삭제 권한이 없는 유저입니다.");
        }

        spotRepository.deleteById(spotId);
        return new ResponseDto<>(200, "Success", "장소 삭제");
    }
}
