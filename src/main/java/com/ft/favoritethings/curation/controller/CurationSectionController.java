package com.ft.favoritethings.curation.controller;

import com.ft.favoritethings.curation.dto.request.CurationSectionCreateDto;
import com.ft.favoritethings.curation.dto.response.ResponseDto;
import com.ft.favoritethings.curation.service.CurationSectionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/curationSection")
public class CurationSectionController {

    private final CurationSectionService curationSectionService;

    /*
    * 큐레이션 섹션 생성
    */
    @Operation(summary = "큐레이션 섹션 생성", description = "큐레이션 섹션을 생성합니다.", tags = {"CurationSection Controller"})
    @PostMapping
    public ResponseDto<?> createCurationSection(@Valid @RequestBody CurationSectionCreateDto curationSectionCreateDto) {
        return curationSectionService.createCurationSection(curationSectionCreateDto);
    }

    /*
     * 모든 큐레이션 섹션 조회
     */
    @Operation(summary = "큐레이션 섹션 목록 조회", description = "모든 큐레이션 섹션을 조회합니다.", tags = {"Curation Controller"})
    @GetMapping("/all")
    public ResponseDto<?> getAllCurationSections(){
        return curationSectionService.getAllCurationSections();
    }

    /*
     * 특정 큐레이션 섹션 조회
     */
    @Operation(summary = "특정 큐레이션 섹션 조회", description = "특정 큐레이션 섹션을 조회합니다.", tags = {"CurationSection Controller"})
    @GetMapping
    public ResponseDto<?> showCurationSection(@RequestParam Long id) {
        return curationSectionService.showCurationSection(id);
    }

    /*
    * 특정 큐레이션에 해당하는 섹션만 조회
    */
//    @Operation(summary = "특정 큐레이션에 해당하는 섹션 조회", description = "특정 큐레이션에 해당하는 섹션을 조회합니다.", tags = {"CurationSection Controller"})
//    @GetMapping
//    public ResponseDto<?> showCurationSection(@RequestParam Long curationId) {
//        return curationSectionService.showCurationSection(curationId);
//    }

    /*
     * 큐레이션 섹션 삭제
     */
    @Operation(summary = "특정 큐레이션 섹션 삭제", description = "큐레이션 섹션을 삭제합니다.", tags = {"CurationSection Controller"})
    @DeleteMapping
    public ResponseDto<?> deleteCurationSection(@RequestParam Long id) {
        return curationSectionService.deleteCurationSection(id);
    }
}
