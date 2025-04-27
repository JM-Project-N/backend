package com.project.projectN.team.entity;

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
public class Team{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "img", columnDefinition = "TEXT")
    private String img;
}

