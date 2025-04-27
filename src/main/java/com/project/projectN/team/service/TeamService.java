package com.project.projectN.team.service;


import com.project.projectN.exception.BusinessLogicException;
import com.project.projectN.exception.ExceptionCode;
import com.project.projectN.redis.RedisService;
import com.project.projectN.team.dto.TeamDto;
import com.project.projectN.team.entity.Team;
import com.project.projectN.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository repository;
    private final RedisService redisService;

    public TeamDto.InviteCode makeInviteCode(String teamId) {
        repository.findById(teamId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BATTERY_CODE_NOT_FOUND));

        String key;

        // 중복되지 않는 6자리 hex 초대 코드 생성
        while (true) {
            key = generateHex6();
            if (!redisService.hasKey(key)) {
                break;
            }
        }

        //10분 유효 입장 코드
        redisService.setValueWithTTL(key, String.valueOf(teamId), Duration.ofMinutes(10));

        return new TeamDto.InviteCode(key);
    }

    public Long checkInviteCodeIsValid(String key) {
        if(!redisService.hasKey(key))
            throw new BusinessLogicException(ExceptionCode.BATTERY_CODE_NOT_FOUND);

        // 1회성 입장 코드
//        Long findTeamId = Long.valueOf(redisService.getValue(key));
//        redisService.deleteValue(key);
//        return findTeamId;

        return Long.valueOf(redisService.getValue(key));
    }

    private String generateHex6() {
        int random = (int) (Math.random() * 0x1000000); // 0 ~ FFFFFF
        return String.format("%06X", random); // 대문자 6자리 hex
    }

    public void createTeam(Team team) {
        Optional<Team> findTeam = repository.findById(team.getTeamId());
        if(findTeam.isPresent()){
            throw new BusinessLogicException(ExceptionCode.TEAM_ID_CONFLICT);
        }
        repository.save(team);
    }
}
