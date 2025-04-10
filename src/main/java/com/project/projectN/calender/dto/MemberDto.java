package com.project.projectN.calender.dto;

import com.project.projectN.calender.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostTest {
        @NotNull(message = "이름은 필수 항목입니다.")
        private String nickname;

        @NotNull(message = "이메일은 필수 항목입니다.")
        private String email;

        @NotNull(message = "닉네임은 필수 항목입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?`~]{8,15}$",
                message = "비밀번호는 8자이상 15자 이하의 알파벳, 숫자, 특수문자만 포함할 수 있습니다.")
        private String password;

        @NotNull(message = "이름은 필수 항목입니다.")
        private String name = "test";

        @NotNull
        private String phone = "00000000000";

        @NotNull
        private String gender = "M";

        @NotNull
        private Member.RegistType registType = Member.RegistType.TEST;

        @NotNull
        private Member.Status status = Member.Status.MANAGER;
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
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?`~]{8,15}$",
                message = "비밀번호는 8자이상 15자 이하의 알파벳, 숫자, 특수문자만 포함할 수 있습니다.")
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
}
