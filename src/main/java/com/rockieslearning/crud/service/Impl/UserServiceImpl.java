package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.Role;
import com.rockieslearning.crud.entity.RoleName;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.request.SignupRequest;
import com.rockieslearning.crud.payload.response.JwtResponse;
import com.rockieslearning.crud.payload.response.MessageResponse;
import com.rockieslearning.crud.repository.RoleRepository;
import com.rockieslearning.crud.repository.UserDetailRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.security.jwt.JwtUtils;
import com.rockieslearning.crud.security.services.UserDetailsImpl;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDetailRepository userDetailRepository ;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;






    @Override
    public List<UserDto> retrieveUsers() {

        List<User> users =  userRepository.findAll();

        return new UserDto().toListDto(users);
    }

    @Override
    public UserDto getUserById(Long id) throws ResourceNotFoundException{

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + id));
        return new UserDto().toDto(user);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));
        userRepository.delete(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) throws ResourceNotFoundException, BadRequestException{


        User existUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));


        existUser.setEmail(userDto.getEmail());
        existUser.setPhone(userDto.getPhone());
        existUser.setPassword(userDto.getPassword());
        //existUser.setRoles(userDto.getRoles());

        User user = new User();
        try {
            user = userRepository.save(existUser);
            return new UserDto().toDto(user);
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }
    }

    @Override
    public List<UserDetail> getListDetailByUserId(Long userId) throws ResourceNotFoundException {
        List<UserDetail> userDetails = new ArrayList<>();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));

        userDetails = userDetailRepository.findByUser(user);
        return  userDetails;
    }

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        // TODO, authenticate when login
        // Username, pass from client
        // com.nashtech.rookies.security.WebSecurityConfig.configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // on this step, we tell to authenticationManager how we load data from database
        // and the password encoder
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhone(), loginRequest.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getPhone(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public UserDto signUp(SignupRequest signupRequest) {
        if (userRepository.existsByPhone(signupRequest.getPhone())) {
            throw new BadRequestException("Error: Phone is already taken!");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signupRequest.getPhone(), signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));



        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
         User saveUser = userRepository.save(user);

        return new UserDto().toDto(saveUser);
    }


    @Override
    public UserDto saveUser(UserDto userDto) throws BadRequestException {
        User user = new UserDto().toEntity(userDto);
        User saveUser = new User();

        try {
            saveUser = userRepository.save(user);
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }

        return new UserDto().toDto(saveUser);
    }





}
