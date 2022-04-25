package com.company.rest;

import com.company.domain.model.request.SignInRequest;
import com.company.domain.model.request.SignUpRequest;
import com.company.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Long> signIn(@RequestBody SignInRequest loginRequest, HttpServletResponse response) {
        return ResponseEntity.ok(userService.loginUser(response, loginRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<Long> signUp(@RequestBody SignUpRequest signupRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(signupRequest));
    }
}
