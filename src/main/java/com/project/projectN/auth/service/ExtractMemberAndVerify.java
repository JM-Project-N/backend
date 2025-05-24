package com.project.projectN.auth.service;

import com.project.projectN.exception.BusinessLogicException;
import com.project.projectN.exception.ExceptionCode;
import com.project.projectN.member.entity.Member;
import com.project.projectN.member.repository.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExtractMemberAndVerify {

    /**
     * 사용자 인증 정보 principal을 통해 토큰 null check 및 토큰 유효성 검증 + Member 값 찾아주는 메서드
     *
     * @param memberRepository 사용자 repository
     * @return 찾아낸 Member
     *
     */
    public Member extractMemberFromPrincipal(MemberRepository memberRepository)  {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.equals("anonymousUser")) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MEMBER);
        }

        return memberRepository.findByEmail(principal.toString())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
