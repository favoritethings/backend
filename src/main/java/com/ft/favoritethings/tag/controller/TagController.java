package com.ft.favoritethings.tag.controller;

import com.ft.favoritethings.common.dto.response.ResponseDto;
import com.ft.favoritethings.tag.dto.request.TagCreateDto;
import com.ft.favoritethings.tag.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
@Slf4j
public class TagController {

    private final TagService tagService;

    /*
     * 태그 생성
     */
    @Operation(summary = "새로운 태그 생성", description = "새로운 태그 생성.", tags = {"Tag Controller"})
    @PostMapping
    public ResponseDto<?> createTag(@RequestBody TagCreateDto tagCreateDto) {
        return tagService.createTag(tagCreateDto);
    }

    /*
     * 모든 태그 조회
     */
    @Operation(summary = "모든 태그 목록 조회", description = "모든 태그 목록 조회.", tags = {"Tag Controller"})
    @GetMapping("/all")
    public ResponseDto<?> getAllTags() {
        return tagService.getAllTags();
    }

    /*
     * 특정 태그 조회
     */
    @Operation(summary = "특정 태그 조회", description = "특정 태그 조회.", tags = {"Tag Controller"})
    @GetMapping
    public ResponseDto<?> showSpecificTag(@RequestParam Long id){
        return tagService.showTag(id);
    }

    /*
     * 태그 삭제
     */
    @Operation(summary = "태그 삭제", description = "태그 삭제.", tags = {"Tag Controller"})
    @DeleteMapping
    public ResponseDto<?> deleteTag(@RequestParam Long id) {
        return tagService.deleteTag(id);
    }
}
