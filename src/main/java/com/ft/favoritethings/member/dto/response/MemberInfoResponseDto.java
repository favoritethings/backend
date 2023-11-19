package com.ft.favoritethings.member.dto.response;

import com.ft.favoritethings.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {
    private String email;
    private String nickname;

    public MemberInfoResponseDto(Member user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
