package com.project.projectN.calender.service;

import com.project.projectN.calender.entity.Member;
import com.project.projectN.calender.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createUser(Member member){
//        member.setMemberId(1);
        return memberRepository.save(member);
    }

//    public Member findMember();
}
