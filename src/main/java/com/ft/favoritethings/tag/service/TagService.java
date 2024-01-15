package com.ft.favoritethings.tag.service;

import com.ft.favoritethings.common.dto.response.ResponseDto;
import com.ft.favoritethings.tag.dto.request.TagCreateDto;
import com.ft.favoritethings.tag.entity.Tag;
import com.ft.favoritethings.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository tagRepository;

    public ResponseDto<?> createTag(TagCreateDto tagCreateDto) {

        Tag tag = Tag.builder()
                .name(tagCreateDto.getName())
                .build();

        tagRepository.save(tag);

        return ResponseDto.success("태그 생성");
    }

    public ResponseDto<?> getAllTags() {

        List<Tag> tags = tagRepository.findAll();

        return new ResponseDto<>(200, "Success", tags);
    }

    public ResponseDto<?> showTag(Long tagId) {

        Optional<Tag> tagOptional = tagRepository.findById(tagId);

        if (tagOptional.isPresent()) {
            return ResponseDto.success(tagOptional);
        } else {
            log.info("태그가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Tag not found", "태그가 존재하지 않습니다.");
        }
    }

    public ResponseDto<?> deleteTag(Long tagId) {

        Optional<Tag> tagOptional = tagRepository.findById(tagId);

        if (tagOptional.isEmpty()) {
            log.info("태그가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Tag not found", "태그가 존재하지 않습니다.");
        }

        tagRepository.deleteById(tagId);

        return new ResponseDto<>(200, "Success", "태그 삭제");
    }
}
