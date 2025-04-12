package com.project.projectN.moneyLog.mapper;

import com.project.projectN.moneyLog.dto.MemberDto;
import com.project.projectN.moneyLog.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member userPostToUser(MemberDto.Post post);
    Member userPostTestToUser(MemberDto.PostTest post);
}
