package com.project.projectN.member.controller;

import com.project.projectN.member.dto.MemberDto;
import com.project.projectN.member.entity.Member;
import com.project.projectN.member.mapper.MemberMapper;
import com.project.projectN.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {
    private final static String USER_DEFAULT_URL = "/auth";
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signUpUser(@Validated @RequestBody MemberDto.Post requestBody){
        System.out.println("테스트");
        Member member = memberMapper.userPostToUser(requestBody);
        memberService.createUser(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
