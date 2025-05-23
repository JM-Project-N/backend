package com.project.projectN.member.dto;

import com.project.projectN.member.entity.Member;
import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MemberDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class TestPost{
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostLogin{
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostLoginKakao{
        private String email;
        private Long Id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotNull(message = "이름은 필수 항목입니다.")
        private String name;

        @NotNull(message = "이메일은 필수 항목입니다.")
        private String email;

        @NotNull(message = "닉네임은 필수 항목입니다.")
//        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?`~]{8,15}$",
//                message = "비밀번호는 8자이상 15자 이하의 알파벳, 숫자, 특수문자만 포함할 수 있습니다.")
        private String password;

        @NotNull
        private String phone;

        @NotNull
        private String gender;

        @NotNull
        private String nickname;

        @NotNull
        private Member.RegistType registType;

        @NotNull
        private Member.Status status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class DuplicationEmailCheck{
        @NotNull
        @Email
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class DuplicationNicknameCheck{
        @NotNull
        private String nickname;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String name;
        private String email;
        private String password;
        private String phone;
        private String gender;
        private String nickname;
        private Member.RegistType registType;
        private Member.Status status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseLogin {
        private Boolean success;
        private String token;
        private Boolean isNewUser;
        private String message;
    }
}
