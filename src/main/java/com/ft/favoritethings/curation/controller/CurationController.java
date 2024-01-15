package com.ft.favoritethings.curation.controller;

import com.ft.favoritethings.curation.dto.request.CurationCreateDto;
import com.ft.favoritethings.common.dto.response.ResponseDto;
import com.ft.favoritethings.curation.service.CurationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/curation")
public class CurationController {

    private final CurationService curationService;

    /*
    * 큐레이션 생성
    */
    @Operation(summary = "큐레이션 생성", description = "큐레이션을 생성합니다.", tags = {"Curation Controller"})
    @PostMapping
    public ResponseDto<?> createCuration(@RequestBody CurationCreateDto curationCreateDto) {
        return curationService.createCuration(curationCreateDto);
    }

    /*
     * 모든 큐레이션 조회
     */
    @Operation(summary = "큐레이션 목록 조회", description = "모든 큐레이션을 조회합니다.", tags = {"Curation Controller"})
    @GetMapping("/all")
    public ResponseDto<?> getAllCurations(){
        return curationService.getAllCurations();
    }

    /*
    * 특정 큐레이션 조회
    */
    @Operation(summary = "특정 큐레이션 조회", description = "특정 큐레이션을 조회합니다.", tags = {"Curation Controller"})
    @GetMapping
    public ResponseDto<?> showCuration(@RequestParam Long id) {
        return curationService.showCuration(id);
    }

    /*
    * 큐레이션 삭제
    */
    @Operation(summary = "특정 큐레이션 삭제", description = "특정 큐레이션을 삭제합니다.", tags = {"Curation Controller"})
    @DeleteMapping
    public ResponseDto<?> deleteCuration(@RequestParam Long id) {
        return curationService.deleteCuration(id);
    }

    /*
    * 홈 큐레이션 조회
    */
    @Operation(summary = "홈 큐레이션 조회", description = "홈 큐레이션을 조회합니다.", tags = {"Curation Controller"})
    @GetMapping("/home")
    public ResponseDto<?> getHomeCurations(){
        return curationService.getHomeCurations();
    }

    /*
    * 큐레이션에 장소 추가
    */
    @Operation(summary = "큐레이션 컨텐츠 추가", description = "큐레이션에 컨텐츠를 추가합니다.", tags = {"Curation Controller"})
    @PostMapping("/spot")
    public ResponseDto<?> postCuratioinSpot(@RequestParam Long curationId, @RequestParam Long spotId) {
        return curationService.postCurationSpot(curationId, spotId);
    }

    /*
     * 큐레이션에 장소 삭제
     */
    @Operation(summary = "큐레이션 컨텐츠 삭제", description = "큐레이션에 컨텐츠를 삭제합니다.", tags = {"Curation Controller"})
    @DeleteMapping("/spot")
    public ResponseDto<?> deleteCurationSpot(@RequestParam Long curationId, @RequestParam Long spotId) {
        return curationService.deleteCurationSpot(curationId, spotId);
    }

}
