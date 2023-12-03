package com.ft.favoritethings.member.controller;

import com.ft.favoritethings.member.dto.request.MemberLoginRequestDto;
import com.ft.favoritethings.member.dto.request.MemberRequestDto;
import com.ft.favoritethings.member.dto.request.ValidatorEmailDto;
import com.ft.favoritethings.member.dto.response.ResponseDto;
import com.ft.favoritethings.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    /*
     * 중복확인
     */
    @Operation(summary = "이메일 중복 확인", description = "이메일 중복 확인.", tags = { "Member Controller" })
    @PostMapping("/checkDuplicate")
    public ResponseDto<?> checkDuplicate(@Valid @RequestBody ValidatorEmailDto validatorEmailDto){
        return memberService.checkDuplicate(validatorEmailDto);
    }

    /*
     * 회원가입
     */
    @Operation(summary = "회원 가입 요청", description = "회원 가입.", tags = { "Member Controller" })
    @PostMapping("/signup")
    public ResponseDto<?> signup(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        return memberService.signup(memberRequestDto);
    }

    /*
     * 로그인
     */
    @Operation(summary = "회원 로그인", description = "회원 로그인.", tags = { "Member Controller" })
    @GetMapping("/login")
    public ResponseDto<?> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto, HttpServletResponse response) {
        return memberService.login(memberLoginRequestDto, response);
    }

}
