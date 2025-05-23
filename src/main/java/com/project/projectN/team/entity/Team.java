package com.project.projectN.team.entity;

import com.project.projectN.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "team")  // 예약어 처리 때문에 백틱 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team extends Auditable {

    @Id
    @Column(name = "team_id", nullable = false)
    private String teamId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "team_name", length = 50, nullable = false)
    private String teamName;

    @Column(name = "img", columnDefinition = "TEXT")
    private String img;
}

