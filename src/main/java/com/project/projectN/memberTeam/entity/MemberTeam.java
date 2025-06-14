package com.project.projectN.memberTeam.entity;

import com.project.projectN.memberTeam.entity.multiKey.memberTeamMk;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_team") // 테이블 이름은 언더스코어 그대로
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(memberTeamMk.class)
public class MemberTeam {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Id
    @Column(name = "team_id", nullable = false)
    private String teamId;

    @Column(name = "role", length = 40, nullable = false)
    private String role;
}
