package com.company.domain.service.impl;

import com.company.domain.model.enums.RoleEnum;
import com.company.domain.model.request.SignInRequest;
import com.company.domain.model.request.SignUpRequest;
import com.company.domain.service.UserService;
import com.company.exeption.EntityAlreadyExistsException;
import com.company.persistence.entity.RoleEntity;
import com.company.persistence.entity.UserEntity;
import com.company.persistence.repository.RoleRepository;
import com.company.persistence.repository.UserRepository;
import com.company.security.config.jwt.JwtConfig;
import com.company.security.config.jwt.JwtTokenProvider;
import com.company.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Log4j2(topic = "USER-SERVICE")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtConfig jwtConfig;

    @Override
    @Transactional
    public Long addUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            log.error("Entity with username " + signUpRequest.getUsername() + " is already exists");
            throw new EntityAlreadyExistsException("username");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            log.error("Entity with email " + signUpRequest.getEmail() + " is already exists");
            throw new EntityAlreadyExistsException("email");
        }

        var source = new UserEntity(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            passwordEncoder.encode(signUpRequest.getPassword()));

        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
        .orElseThrow(() -> new EntityNotFoundException("name " + RoleEnum.ROLE_USER));
        roles.add(userRole);

        source.setRoles(roles);
        var result = userRepository.save(source);
        return result.getId();
    }

    public String loginUser(HttpServletResponse response, SignInRequest signInRequest) throws AuthenticationException {

        try {
            Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()));

            UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtTokenProvider.createToken(authentication);
            response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
            return token;

        } catch (AuthenticationException exception) {
            log.error("Unauthorized. Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
