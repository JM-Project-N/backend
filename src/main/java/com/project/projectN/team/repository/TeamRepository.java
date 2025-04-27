package com.project.projectN.team.repository;

import com.project.projectN.member.entity.Member;
import com.project.projectN.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Member> findByEmail(String email);

}