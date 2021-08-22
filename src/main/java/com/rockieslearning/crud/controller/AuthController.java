package com.rockieslearning.crud.controller;


import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.Role;
import com.rockieslearning.crud.entity.RoleName;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.request.SignupRequest;
import com.rockieslearning.crud.payload.request.SignupRequestV2;
import com.rockieslearning.crud.payload.response.JwtResponse;
import com.rockieslearning.crud.payload.response.MessageResponse;
import com.rockieslearning.crud.repository.RoleRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.security.jwt.JwtUtils;
import com.rockieslearning.crud.security.services.UserDetailsImpl;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse JwtResponse = userService.signIn(loginRequest);

        return new ResponseEntity<>(JwtResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        UserDto userDto = userService.signUp(signUpRequest);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }



    @PostMapping("/signupv2")
    public ResponseEntity<UserDto> registerUserV2(@Valid @RequestBody SignupRequestV2 signUpRequest) {

        UserDto userDto = new UserDto();
        try{
             userDto = userService.signUpV2(signUpRequest);
        }catch (Exception e){
            System.out.println(e.getMessage() + e.getCause()+ e.getStackTrace()+ e.getLocalizedMessage());
        }


        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}