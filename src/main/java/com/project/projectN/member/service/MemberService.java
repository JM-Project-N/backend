package com.project.projectN.member.service;

import com.project.projectN.exception.BusinessLogicException;
import com.project.projectN.exception.ExceptionCode;
import com.project.projectN.member.entity.Member;
import com.project.projectN.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public Member createUser(Member member){
//        member.setMemberId(1);
        return repository.save(member);
    }

    public Boolean loginUser(Member member) {
        Member findMember = repository.findByNameAndEmail(member.getName(), member.getEmail())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return true;

    }

//    public Member findMember();
}
