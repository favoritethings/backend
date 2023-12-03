package com.ft.favoritethings.member.service;

import com.ft.favoritethings.jwt.dto.response.TokenDto;
import com.ft.favoritethings.jwt.entity.RefreshToken;
import com.ft.favoritethings.jwt.repository.RefreshTokenRepository;
import com.ft.favoritethings.jwt.security.JwtFilter;
import com.ft.favoritethings.jwt.security.TokenProvider;
import com.ft.favoritethings.member.dto.request.MemberLoginRequestDto;
import com.ft.favoritethings.member.dto.request.MemberRequestDto;
import com.ft.favoritethings.member.dto.request.ValidatorEmailDto;
import com.ft.favoritethings.member.dto.response.ResponseDto;
import com.ft.favoritethings.member.entity.Member;
import com.ft.favoritethings.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;


    /*
     * 회원가입
     */
    @Transactional
    public ResponseDto<?> signup(MemberRequestDto memberRequestDto) {

        if (memberRepository.existsByEmail(memberRequestDto.getEmail())){
            log.info("중복된 이메일입니다");
            return ResponseDto.fail(400,"중복된 이메일입니다.",memberRequestDto.getEmail()+"는 중복된 이메일입니다.");
        } else if (!memberRequestDto.getPassword().equals(memberRequestDto.getPasswordConform())){
            log.info("비밀번호가 일치하지 않습니다");
            return ResponseDto.fail(400,"비밀번호가 일치하지 않습니다.","비밀번호가 일치하지 않습니다.");
        } else if (memberRepository.existsByPhone(memberRequestDto.getPhone())) {
            log.info("이미 가입된 번호입니다.");
            return ResponseDto.fail(400,"이미 가입된 번호입니다.",memberRequestDto.getPhone()+"는 중복된 번호입니다.");
        }

        new Member();
        Member member = Member.builder()
                .email(memberRequestDto.getEmail())
                .nickname(memberRequestDto.getNickname())
                .phone(memberRequestDto.getPhone())
                .password(passwordEncoder.encode(memberRequestDto.getPassword()))
                .build();

        memberRepository.save(member);

        return ResponseDto.success("회원가입 성공!");
    }

    /*
     * 로그인
     */
    @Transactional
    public ResponseDto<?> login(MemberLoginRequestDto memberLoginRequestDto, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken = memberLoginRequestDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        Member member = memberRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."));

        if(!passwordEncoder.matches(memberLoginRequestDto.getPassword(), member.getPassword()))
            throw new RuntimeException("패스워드가 일치하지 않습니다.");

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        response.setHeader(JwtFilter.AUTHORIZATION_HEADER, JwtFilter.BEARER_PREFIX + tokenDto.getAccessToken());
        response.setHeader("Refresh-Token", tokenDto.getRefreshToken());

        return ResponseDto.success("로그인 성공!");
    }

    /*
     * 중복확인
     */
    public ResponseDto<?> checkDuplicate(ValidatorEmailDto validatorEmailDto) {

        Member findMember = memberRepository.findByEmail(validatorEmailDto.getEmail()).orElse(null);

        if(Objects.isNull(findMember)) {
            return ResponseDto.success(validatorEmailDto.getEmail()+"는 가입할 수 있는 이메일입니다.");
        } else {
            return ResponseDto.fail(400,"중복된 이메일입니다.",findMember.getEmail()+"는 중복된 이메일입니다.");
        }
    }

}
