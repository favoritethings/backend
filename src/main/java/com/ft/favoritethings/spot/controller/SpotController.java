package com.ft.favoritethings.spot.controller;

import com.ft.favoritethings.member.entity.Member;
import com.ft.favoritethings.spot.dto.request.SpotCreateDto;
import com.ft.favoritethings.spot.dto.response.ResponseDto;
import com.ft.favoritethings.spot.service.SpotService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spot")
@Slf4j
public class SpotController {

    private final SpotService spotService;


    /*
     * 장소 생성
     */
    @Operation(summary = "새로운 장소 생성", description = "새로운 장소 생성.", tags = {"Spot Controller"})
    @PostMapping
    public ResponseDto<?> createSpot(@Valid @RequestBody  SpotCreateDto spotCreateDto, @AuthenticationPrincipal Member member){
        return spotService.createSpot(spotCreateDto, member);
    }

    /*
     * 모든 장소 목록 조회
     */
    @Operation(summary = "모든 장소 목록 조회", description = "모든 장소 목록 조회.", tags = {"Spot Controller"})
    @GetMapping("/all")
    public ResponseDto<?> getAllSpots(){
        return spotService.getAllSpots();

    }


    /*
     * 특정 장소 조회
     */
    @Operation(summary = "특정 장소 조회", description = "특정 장소 조회.", tags = {"Spot Controller"})
    @GetMapping
    public ResponseDto<?> showSpecificSpot(@RequestParam Long id){
        return spotService.showSpecificSpot(id);
    }


    /*
     * 장소 삭제
     */
    @Operation(summary = "장소 삭제", description = "장소 삭제.", tags = {"Spot Controller"})
    @DeleteMapping
    public ResponseDto<?> deleteSpot(@RequestParam Long id, @AuthenticationPrincipal Member member){
        return spotService.deleteSpot(id, member);
    }
}
