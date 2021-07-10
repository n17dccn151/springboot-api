package com.rockieslearning.crud.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.request.SignupRequest;
import com.rockieslearning.crud.payload.response.JwtResponse;
import com.rockieslearning.crud.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testAuthenticateUser() throws Exception {


        List<String> roles = new ArrayList<>();
        roles.add("USER");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone("0123123123");
        loginRequest.setPassword("admin12345");


        JwtResponse jwtResponse = new JwtResponse("eyJhbGciOi", Long.valueOf(1), "0123123123", "vivu@gmail.com", roles);


        Mockito.when(userService.signIn(loginRequest)).thenReturn(jwtResponse);

        String json = mapper.writeValueAsString(loginRequest);

        mockMvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }


    @Test
    public void testRegisterUser() throws Exception {

        Set<String> roles = new HashSet<>();
        roles.add("USER");
        UserDto userDto = new UserDto();
        userDto.setPhone("0123123123");
        userDto.setRoles(roles);
        userDto.setPassword("123123");
        userDto.setEmail("vivu@gmail.com");


        Mockito.when(userService.signUp(ArgumentMatchers.any())).thenReturn(userDto);
        String json = mapper.writeValueAsString(userDto);
        mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.phone", Matchers.equalTo("0123123123")))
                .andExpect(jsonPath("$.email", Matchers.equalTo("vivu@gmail.com")));
        //.andExpect(jsonPath("$.roles[0]").value("ROLE_USER"));

    }
}