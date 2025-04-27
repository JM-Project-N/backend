package com.project.projectN.team.mapper;

import com.project.projectN.team.dto.TeamDto;
import com.project.projectN.team.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team teamDtoPostToTeam(TeamDto.Post post);
}
