package com.project.projectN.calender.mapper;

import com.project.projectN.calender.dto.MemberDto;
import com.project.projectN.calender.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member userPostToUser(MemberDto.Post post);
    Member userPostTestToUser(MemberDto.PostTest post);
}
