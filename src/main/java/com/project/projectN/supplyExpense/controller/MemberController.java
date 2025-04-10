package com.project.projectN.supplyExpense.controller;

import com.project.projectN.supplyExpense.dto.MemberDto;
import com.project.projectN.supplyExpense.entity.Member;
import com.project.projectN.supplyExpense.mapper.MemberMapper;
import com.project.projectN.supplyExpense.service.MemberService;
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
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final static String USER_DEFAULT_URL = "/member";
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @PostMapping("/test")
    public ResponseEntity signUpMemberTest(@Validated @RequestBody MemberDto.PostTest requestBody){
        System.out.println("테스트");
        Member member = memberMapper.userPostTestToUser(requestBody);
        memberService.createUser(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity signUpMember(@Validated @RequestBody MemberDto.Post requestBody){
        Member member = memberMapper.userPostToUser(requestBody);
        memberService.createUser(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping
//    public ResponseEntity getMember() {
//        Member findMember = memberService.findMember();
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(memberMapper.memberToResponseDto(findMember)), HttpStatus.OK);
//    }
}
