package com.project.projectN.memberTeam.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_team") // 테이블 이름은 언더스코어 그대로
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_team_id")
    private Long memberTeamId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "role", length = 40, nullable = false)
    private String role;
}
