package com.project.projectN.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RedisService redisService;

    public void saveRefreshToken(String email, String refreshToken) {
        redisService.setValueWithTTL("RT:" + email, refreshToken, Duration.ofDays(7));
    }

    public String getRefreshToken(String email) {
        return redisService.getValue("RT:" + email);
    }

    public void deleteRefreshToken(String email) {
        redisService.deleteValue("RT:" + email);
    }
}