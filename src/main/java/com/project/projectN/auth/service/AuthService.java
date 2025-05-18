package com.project.projectN.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projectN.auth.dto.AuthDto;
import com.project.projectN.exception.BusinessLogicException;
import com.project.projectN.exception.ExceptionCode;
import com.project.projectN.member.entity.Member;
import com.project.projectN.member.repository.MemberRepository;
import com.project.projectN.redis.TokenService;
import com.project.projectN.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository repository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthDto.KakaoUserInfoDto getUserInfoFromAccessToken(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                String.class
        );

        try {
            // JSON 응답 → DTO 매핑
            AuthDto.KakaoUserInfoDto userInfo = objectMapper.readValue(response.getBody(), AuthDto.KakaoUserInfoDto.class);

            // 예시: 꺼내서 사용
            Long id = userInfo.getId();
            String email = userInfo.getKakao_account().getEmail();
            String nickname = userInfo.getProperties().getNickname();

            // 필요시 로그 출력
            System.out.println("id = " + id);
            System.out.println("email = " + email);
            System.out.println("nickname = " + nickname);
            boolean isNewUser = isNewMemberCheckKakaoVersion(id, email);

            AuthDto.Response ret = new AuthDto.Response();
            ret.setNickname(nickname);
            ret.setIsNewUser(isNewUser);
            ret.setJwtToken("여기에추가해야함");
            return userInfo;

        } catch (Exception e) {
            throw new RuntimeException("카카오 사용자 정보 파싱 실패", e);
        }
    }

    public boolean isNewMemberCheckKakaoVersion(Long id, String email) {
        Optional<Member> findMember = repository.findByIdAndEmail(id,email);
        if(findMember.isEmpty()){
            Member member = new Member();
            member.setId(id);
            member.setEmail(email);
            member.setRegistType(Member.RegistType.KAKAO);
            member.setStatus(Member.Status.NORMAL);
            repository.save(member);
            return true;
        }
        return false;
    }

    private String issueJwtToken(String email) {
        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);
        tokenService.saveRefreshToken(email, refreshToken);
        return accessToken; // 필요한 경우 accessToken과 refreshToken을 함께 반환하는 구조로 변경 가능
    }


}

