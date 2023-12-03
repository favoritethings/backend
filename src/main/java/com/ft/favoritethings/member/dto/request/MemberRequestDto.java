package com.ft.favoritethings.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{6,12}$";

    private String email;

    @Size(min = 2, max = 8, message = "닉네임은 2자 이상 8자 이하로 생성해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "닉네임에는 한글과 영문자만 사용할 수 있습니다.")
    private String nickname;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-0000-0000 형식으로 입력해주세요.")
    private String phone;

    @Pattern(regexp = PASSWORD_PATTERN, message = "비밀번호는 6자 이상 소문자와 특수문자를 섞어서 생성해주세요.")
    private String password;
    @Pattern(regexp = PASSWORD_PATTERN, message = "비밀번호는 6자 이상 소문자와 특수문자를 섞어서 생성해주세요.")
    private String passwordConform;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
