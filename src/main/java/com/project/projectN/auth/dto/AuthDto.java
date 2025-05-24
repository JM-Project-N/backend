package com.project.projectN.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthDto {


    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetToken {
        private String accessToken;
    }

    @Data
    @NoArgsConstructor
    public static class Response {
        private String jwtToken;
        private String nickname;
        private Boolean isNewUser;
    }

    @Data
    @NoArgsConstructor
    public static class KakaoUserInfoDto {
        private Long id;
        private String connected_at;
        private Properties properties;
        private KakaoAccount kakao_account;

        @Data
        public static class Properties {
            private String nickname;
            private String profile_image;
            private String thumbnail_image;
        }

        @Data
        public static class KakaoAccount {
            private Boolean has_email;
            private Boolean email_needs_agreement;
            private Boolean is_email_valid;
            private Boolean is_email_verified;
            private String email;
            private Profile profile;

            @Data
            public static class Profile {
                private String nickname;
                private String thumbnail_image_url;
                private String profile_image_url;
                private Boolean is_default_image;
            }
        }
    }
}
