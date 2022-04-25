package com.company.domain.service;


import com.company.domain.model.request.SignInRequest;
import com.company.domain.model.request.SignUpRequest;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    Long addUser(SignUpRequest signUpRequest);
    Long loginUser(HttpServletResponse response, SignInRequest signInRequest);
}
