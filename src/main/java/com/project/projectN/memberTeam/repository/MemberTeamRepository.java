package com.project.projectN.memberTeam.repository;

import com.project.projectN.member.entity.Member;
import com.project.projectN.memberTeam.entity.MemberTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface MemberTeamRepository extends JpaRepository<MemberTeam, Long> {

}
