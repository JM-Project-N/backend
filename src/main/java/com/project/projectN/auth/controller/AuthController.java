package com.project.projectN.auth.controller;


import com.project.projectN.auth.dto.AuthDto;
import com.project.projectN.auth.service.AuthService;
import com.project.projectN.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/kakao")
    public ResponseEntity getAccessTokenValid(@RequestBody AuthDto.GetToken request) {
        AuthDto.Response response = service.getUserInfoFromAccessToken(request.getAccessToken());
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
