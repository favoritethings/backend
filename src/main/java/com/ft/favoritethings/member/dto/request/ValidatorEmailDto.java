package com.ft.favoritethings.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class ValidatorEmailDto {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

}
