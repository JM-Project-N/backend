package com.project.projectN.member.mapper;

import com.project.projectN.member.dto.MemberDto;
import com.project.projectN.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member userPostToUser(MemberDto.Post post);
    Member userPostTestToUser(MemberDto.TestPost post);

    Member userPostLoginToUser(MemberDto.PostLogin postLogin);
}
