package com.ft.favoritethings.spot.service;

import com.ft.favoritethings.curation.entity.Curation;
import com.ft.favoritethings.curation.repository.CurationRepository;
import com.ft.favoritethings.member.entity.AccountType;
import com.ft.favoritethings.member.entity.Member;
import com.ft.favoritethings.spot.dto.request.SpotCreateDto;
import com.ft.favoritethings.common.dto.response.ResponseDto;
import com.ft.favoritethings.spot.entity.Spot;
import com.ft.favoritethings.spot.repository.SpotRepository;
import com.ft.favoritethings.tag.entity.Tag;
import com.ft.favoritethings.tag.repository.TagRepository;
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
    public final CurationRepository curationRepository;
    public final TagRepository tagRepository;

    public ResponseDto<?> createSpot(SpotCreateDto spotCreateDto) {

        Spot spot = Spot.builder()
                .title(spotCreateDto.getTitle())
                .subTitle(spotCreateDto.getSubTitle())
                .description(spotCreateDto.getDescription())
                .address(spotCreateDto.getAddress())
                .openingHour(spotCreateDto.getOpeningHour())
                .isParking(spotCreateDto.getIsParking())
                .featured_image(spotCreateDto.getFeaturedImage())
                .build();

        spotRepository.save(spot);

        return ResponseDto.success("장소 생성");
    }

    public ResponseDto<?> getAllSpots() {

        List<Spot> spots = spotRepository.findAll();
        return new ResponseDto<>(200, "Success", spots);
    }

    public ResponseDto<?> showSpot(Long spotId) {

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

    public ResponseDto<?> postCurationSpot(List<Spot> spotList, Long curationId) {

        Optional<Curation> curationOptional = curationRepository.findById(curationId);

        if(curationOptional.isEmpty()) {
            log.info("큐레이션이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Curation not found", "큐레이션이 존재하지 않습니다.");
        }

        for (Spot spot : spotList) {
            Optional<Spot> spotOptional = spotRepository.findById(spot.getId());

            if(spotOptional.isEmpty()) {
                log.info("장소가 존재하지 않습니다.");
                return ResponseDto.fail(404, "Spot not found", "장소가 존재하지 않습니다.");
            }

            Spot existingSpot = spotOptional.get();
            Curation curation = curationOptional.get();
            existingSpot.setCuration(curation);

            spotRepository.save(existingSpot);
        }

        return ResponseDto.success(curationOptional.get());
    }

    public ResponseDto<?> postTag(Long spotId, Long tagId) {

        Optional<Spot> spotOptional = spotRepository.findById(spotId);

        if(spotOptional.isEmpty()) {
            log.info("장소가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Spot not found", "장소가 존재하지 않습니다.");
        }

        Optional<Tag> tagOptional = tagRepository.findById(tagId);

        if(tagOptional.isEmpty()) {
            log.info("태그가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Tag not found", "태그가 존재하지 않습니다.");
        }

        Spot spot = spotOptional.get();
        Tag tag = tagOptional.get();

        spot.setTagList(tag);
        spotRepository.save(spot);

        return ResponseDto.success(spot);
    }
}
