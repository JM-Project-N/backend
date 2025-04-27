package com.project.projectN.team.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TeamDto {

    @AllArgsConstructor
    @Setter
    @Getter
    public static class InviteCode {
        private String inviteCode;
    }
}
