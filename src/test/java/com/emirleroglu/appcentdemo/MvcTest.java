package com.emirleroglu.appcentdemo;

import com.emirleroglu.appcentdemo.controller.AuthController;
import com.emirleroglu.appcentdemo.model.AppUser;
import com.emirleroglu.appcentdemo.payload.request.LoginRequest;

import com.emirleroglu.appcentdemo.repository.AppUserRepository;
import com.emirleroglu.appcentdemo.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static com.emirleroglu.appcentdemo.model.ERole.ROLE_USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
@ContextConfiguration
public class MvcTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private AuthController controller;
    @Mock
    AppUserRepository repository;
    @Mock
    PasswordEncoder encoder;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtUtils jwtUtils;


    @Before
    public void setup() {


        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }


    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void CreateUser() throws Exception {
        AppUser user = new AppUser();
        user.setUsername("Halil");
        user.setAppUserRole(ROLE_USER);
        user.setEmail("emirlerogluhalil@gmail.com");
        user.setPassword("12345");
        user.setId(1L);
        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    /*@Test Login request testini yapamadım.
    public void loginUser() throws Exception {
        AppUser user = new AppUser();
        user.setUsername("Halil");
        user.setAppUserRole(ROLE_USER);
        user.setEmail("emirlerogluhalil@gmail.com");
        user.setPassword("12345");
        user.setId(1L);
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    } */


}
