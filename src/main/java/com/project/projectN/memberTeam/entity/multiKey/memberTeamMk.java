package com.project.projectN.memberTeam.entity.multiKey;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class memberTeamMk implements Serializable {
    private String email;
    private String teamId;
}
