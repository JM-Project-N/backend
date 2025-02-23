package com.project.projectN.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

//    @Column(name = "name", length = 100, nullable = false)
//    private String name;

    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;


}
