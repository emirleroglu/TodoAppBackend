package com.emirleroglu.appcentdemo.controller;

import com.emirleroglu.appcentdemo.model.AppUser;
import com.emirleroglu.appcentdemo.payload.request.LoginRequest;
import com.emirleroglu.appcentdemo.payload.request.SignupRequest;
import com.emirleroglu.appcentdemo.payload.response.JwtResponse;
import com.emirleroglu.appcentdemo.payload.response.MessageResponse;
import com.emirleroglu.appcentdemo.repository.AppUserRepository;
import com.emirleroglu.appcentdemo.security.jwt.JwtUtils;
import com.emirleroglu.appcentdemo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    AppUserRepository userRepository;


    PasswordEncoder encoder;

    AuthenticationManager authenticationManager;

    JwtUtils jwtUtils;


    @Autowired
    public AuthController(AppUserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        AppUser user = new AppUser(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
    }
}
