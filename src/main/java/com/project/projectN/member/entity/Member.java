package com.project.projectN.member.entity;

import com.project.projectN.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "phone", length = 100, nullable = false)
    private String phone;

    @Column(name = "gender", length = 100, nullable = false)
    private String gender;

    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname;

    @Column(name = "regist_type", length = 100, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RegistType registType = RegistType.GUEST;

    @Column(name = "status", length = 100, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.NORMAL;




    ///어떠한 방식으로 회원가입을 진행했는지
    /// 오리진 : 계정연동을 안하고 현 서비스에 직접 가입한 사람
    /// 게스트 : 기본 값
    /// 테스트 : 계정 연동 및 회원가입 테스트를 위해 만들어진 Type
    @Getter
    public enum RegistType {
        KAKAO("카카오"),
        GOOGLE("구글"),
        APPLE("애플"),
        TEST("테스트"),
        ORIGIN("오리진"),
        GUEST("게스트");

        private final String status;

        RegistType(String status) {
            this.status = status;
        }
    }

    @Getter
    public enum Status {
        DELETE("삭제된 사용자"),
        NORMAL("일반 사용자"),
        MANAGER("관리자");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }
}
