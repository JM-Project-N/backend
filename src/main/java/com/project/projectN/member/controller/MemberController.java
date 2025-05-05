package com.project.projectN.member.controller;

import com.project.projectN.dto.SingleResponseDto;
import com.project.projectN.member.dto.MemberDto;
import com.project.projectN.member.entity.Member;
import com.project.projectN.member.mapper.MemberMapper;
import com.project.projectN.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final static String USER_DEFAULT_URL = "/member";
    private final MemberMapper mapper;
    private final MemberService service;

    /**
     * 회원가입 O auth 적용하기 전 테스트용
     * POST : http://[api]/member/test
     * @param requestBody
     * @return
     */
    @PostMapping("/test")
    public ResponseEntity signUpMemberTest(@RequestBody MemberDto.TestPost requestBody){
        System.out.println("테스트");
        Member member = mapper.userPostTestToUser(requestBody);
        service.createUser(member);
//        return new ResponseEntity<>(HttpStatus.OK);
        MemberDto.ResponseLogin responseLogin = new MemberDto.ResponseLogin();
        responseLogin.setToken("token");
        responseLogin.setMessage("테스트 로그인 성공");
        responseLogin.setIsNewUser(true);
        responseLogin.setSuccess(true);
        return new ResponseEntity<>(
                new SingleResponseDto<>(responseLogin),HttpStatus.OK);
    }

    /**
     * 회원 가입용 메서드
     * POST : http://[api]/member/login
     * @param requestBody
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity signUpMember(@Validated @RequestBody MemberDto.PostLogin requestBody){
        Member member = service.loginUser(mapper.userPostLoginToUser(requestBody));
        MemberDto.ResponseLogin responseLogin = new MemberDto.ResponseLogin();
        responseLogin.setToken(String.valueOf(member.getId()));
        responseLogin.setMessage("테스트 로그인 성공");
        responseLogin.setIsNewUser(true);
        responseLogin.setSuccess(true);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping
//    public ResponseEntity getMember() {
//        Member findMember = memberService.findMember();
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(memberMapper.memberToResponseDto(findMember)), HttpStatus.OK);
//    }
}
