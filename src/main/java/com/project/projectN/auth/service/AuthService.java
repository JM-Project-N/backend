package com.project.projectN.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
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

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository repository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String GOOGLE_CLI_ID =
            "963128555369-b11fm3dqqahmhs1f4l1ntpa7qapc4rqr.apps.googleusercontent.com";

    public AuthDto.Response getUserInfoFromAccessTokenForKakao(String accessToken) {
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
            ret.setJwtToken(issueJwtToken(email));
            return ret;

        } catch (Exception e) {
            throw new BusinessLogicException(ExceptionCode.KAKAO_USER_INFO_PARSE_FAILED);
        }
    }



    public AuthDto.Response loginWithGoogle(String idTokenString) throws Exception {
        var transport = GoogleNetHttpTransport.newTrustedTransport();
        var jsonFactory = GsonFactory.getDefaultInstance();

        var verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(GOOGLE_CLI_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new RuntimeException("Invalid ID Token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();

        String email = payload.getEmail();
        String nickname = (String) payload.get("name");
        String picture = (String) payload.get("picture");
        String sub = payload.getSubject(); // 구글 고유 유저 ID

        // 회원 존재 여부 확인
        Member member = repository.findByEmail(email).orElse(null);
        boolean isNewUser = false;

        if (member == null) {
            // 신규 회원 등록
            member = Member.builder()
                    .name("")
                    .email(email)
                    .nickname(nickname)
                    .gender("")
//                    .profileImage(picture)
                    .registType(Member.RegistType.GOOGLE)
                    .build();
            repository.save(member);
            isNewUser = true;
        }

        String jwtToken = jwtTokenProvider.createAccessToken(email); // 예: email 기반 토큰 발급

        return AuthDto.Response.builder()
                .nickname(member.getNickname())
                .isNewUser(isNewUser)
                .jwtToken(jwtToken)
                .build();
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

