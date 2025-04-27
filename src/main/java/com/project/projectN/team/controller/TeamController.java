package com.project.projectN.team.controller;

import com.project.projectN.dto.SingleResponseDto;
import com.project.projectN.team.dto.TeamDto;
import com.project.projectN.team.mapper.TeamMapper;
import com.project.projectN.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService service;
    private final TeamMapper mapper;

    @GetMapping("/code/{team_id}")
    public ResponseEntity MakeTeamInviteCode(@PathVariable("team_id") String teamId){
        return new ResponseEntity<>(
                new SingleResponseDto<>(service.makeInviteCode(teamId)), HttpStatus.OK);
    }

    @PostMapping("/code/valid")
    public ResponseEntity checkInviteCode(@RequestBody TeamDto.InviteCode code) {
        service.checkInviteCodeIsValid(code.getInviteCode());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createTeam(@RequestBody TeamDto.Post post) {
        service.createTeam(mapper.teamDtoPostToTeam(post));
        return ResponseEntity.ok().build();
    }


}
