package com.project.projectN.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotNull(message = "닉네임은 필수 항목입니다.")
        private String nickname;

        @NotNull(message = "이메일은 필수 항목입니다.")
        private String email;

        @NotNull(message = "닉네임은 필수 항목입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?`~]{8,15}$",
                message = "비밀번호는 8자이상 15자 이하의 알파벳, 숫자, 특수문자만 포함할 수 있습니다.")
        private String password;
    }
}
