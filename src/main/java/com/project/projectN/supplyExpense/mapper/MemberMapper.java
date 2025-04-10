package com.project.projectN.supplyExpense.mapper;

import com.project.projectN.supplyExpense.dto.MemberDto;
import com.project.projectN.supplyExpense.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member userPostToUser(MemberDto.Post post);
    Member userPostTestToUser(MemberDto.PostTest post);
}
