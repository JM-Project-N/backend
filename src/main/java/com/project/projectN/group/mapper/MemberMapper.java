package com.project.projectN.group.mapper;

import com.project.projectN.group.dto.MemberDto;
import com.project.projectN.group.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member userPostToUser(MemberDto.Post post);
    Member userPostTestToUser(MemberDto.PostTest post);
}
