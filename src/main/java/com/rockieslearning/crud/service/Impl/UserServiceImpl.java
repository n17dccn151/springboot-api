package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.request.SignupRequest;
import com.rockieslearning.crud.payload.response.JwtResponse;
import com.rockieslearning.crud.repository.*;
import com.rockieslearning.crud.security.jwt.JwtUtils;
import com.rockieslearning.crud.security.services.UserDetailsImpl;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    CartRepository cartRepository;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, UserDetailRepository userDetailRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailRepository = userDetailRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public List<UserDto> retrieveUsers() {

        List<User> users = userRepository.findAll();

        return new UserDto().toListDto(users);
    }

    @Override
    public List<UserDto> retrieveUsers(RoleName role, Pageable pageable) throws BadRequestException {
        List<User> users = new ArrayList<>();

        Role role1 = roleRepository.findByName(role)
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));


        Page<User> userPage;
        try {
            userPage = userRepository.findUserByRoles(role1, pageable);
        } catch (Exception e) {
            throw new BadRequestException("invalid Request " + e.getMessage());
        }

        users = userPage.getContent();
        return new UserDto().toListDto(users);
    }

    @Override
    public List<UserDto> getUserByPhone(RoleName role, String phone, Pageable pageable) throws BadRequestException {
        List<User> users = new ArrayList<>();

        Role role1 = roleRepository.findByName(role)
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));


        Page<User> userPage;
        try {
            userPage = userRepository.findUserByRolesAndPhoneContaining(role1, phone, pageable);
        } catch (Exception e) {
            throw new BadRequestException("invalid Request " + e.getMessage());
        }

        users = userPage.getContent();
        return new UserDto().toListDto(users);
    }

    @Override
    public UserDto getUserById(Long id) throws ResourceNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + id));
        return new UserDto().toDto(user);
    }

    @Override
    public String deleteUser(Long userId) throws ResourceNotFoundException {


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));
        userRepository.delete(user);
        return "deleted";
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) throws ResourceNotFoundException, BadRequestException {


        User existUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));


        existUser.setEmail(userDto.getEmail());
        existUser.setPhone(userDto.getPhone());
//        existUser.setPassword(userDto.getPassword());
        //existUser.setRoles(userDto.getRoles());

        User user = new User();
        try {
            user = userRepository.save(existUser);
            return new UserDto().toDto(user);
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }
    }

    @Override
    public List<UserDetailDto> getListDetailByUserId(Long userId) throws ResourceNotFoundException {
        List<UserDetail> userDetails = new ArrayList<>();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));

        userDetails = userDetailRepository.findByUser(user);

        List<UserDetail> userDetailList = new ArrayList<>();

        userDetails.forEach(userDetail -> {
            if(userDetail.getStatus().equals(UserDetailStatusName.DELETED)){

            }else{
                userDetailList.add(userDetail);
            }
        });


        return new UserDetailDto().toListDto(userDetailList);
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
        AtomicBoolean checkForUserCart = new AtomicBoolean(false);
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            checkForUserCart.set(true);
        } else {
            strRoles.forEach(role -> {
                System.out.println("_________________" + role);
                switch (role.toLowerCase()) {
                    case "role_admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        checkForUserCart.set(true);
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);

                }
            });
        }

        user.setRoles(roles);
        User saveUser = userRepository.save(user);

        if (checkForUserCart.get() == true) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }


        return new UserDto().toDto(saveUser);
    }

    @Override
    public UserDetailDto getUserDetail(Integer id) throws ResourceNotFoundException {
        UserDetail userDetail = userDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("userdetail not found for this id: " + id));

        return new UserDetailDto().toDto(userDetail);
    }


    @Override
    public UserDto saveUser(UserDto userDto) throws BadRequestException {
        User user = new UserDto().toEntity(userDto);
        User saveUser = new User();

        try {
            saveUser = userRepository.save(user);
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }

        return new UserDto().toDto(saveUser);
    }


    @Override
    public UserDetailDto saveUserDetail(Long userId, UserDetailDto userDetailDto) throws BadRequestException {
        UserDetail userDetail = userDetailDto.toEnti(userDetailDto);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id"));
        userDetail.setUser(user);

        List<UserDetail> userDetails = userDetailRepository.findAll();
        if (userDetails.isEmpty()) {
            userDetail.setStatus(UserDetailStatusName.DEFAULT);
        } else {
            userDetail.setStatus(UserDetailStatusName.UNDEFAULT);
        }

        int id = userDetailRepository.save(userDetail).getId();
        try {
            return new UserDetailDto().toDto(userDetailRepository.getById(id));
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }

    }

    @Override
    public UserDetailDto updateUserDetail(Integer detailId, UserDetailDto userDetailDto) throws BadRequestException {
        UserDetail userDetail = userDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("userdetail not found for this id: " + detailId));

        if (orderRepository.findByUserDetail(userDetail).size() >= 1) {

            System.out.println("----------------------------CO dat ahfg-----------------" + userDetail.getStatus());


            UserDetail userDetail1 = new UserDetail();
            userDetail1.setFirstName(userDetailDto.getFirstName());
            userDetail1.setLastName(userDetailDto.getLastName());
            userDetail1.setPhone(userDetailDto.getPhone());
            userDetail1.setAddress(userDetailDto.getAddress());
            userDetail1.setStatus(userDetailDto.getStatus());
            userDetail1.setUser(userDetail.getUser());
            userDetailRepository.save(userDetail1);



            //old
            userDetail.setStatus(UserDetailStatusName.DELETED);
//            userDetailRepository.save(userDetail);


        }else{
            System.out.println("---------------------------KO-CO dat ahfg-----------------" + userDetail.getStatus());

            userDetail.setFirstName(userDetailDto.getFirstName());
            userDetail.setLastName(userDetailDto.getLastName());
            userDetail.setPhone(userDetailDto.getPhone());
            userDetail.setAddress(userDetailDto.getAddress());
            userDetail.setStatus(userDetailDto.getStatus());
        }



        System.out.println("---------------------------------------------" + userDetail.getStatus());
        try {
            return new UserDetailDto().toDto(userDetailRepository.save(userDetail));
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }
    }

    @Override
    public UserDetailDto updateUserDetailStatus(Integer detailId, UserDetailStatusName userDetailStatusName) throws BadRequestException {

        UserDetail userDetail1 = userDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("userdetail not found for this id: " + detailId));

        if (userDetailRepository.findListUserDetailByStatusAndUser(UserDetailStatusName.DEFAULT, userDetail1.getUser()).size() < 1) {
            throw new BadRequestException("invalid request");
        }

        try {

            if (userDetailStatusName.equals(UserDetailStatusName.DEFAULT)) {
                UserDetail userDetail = userDetailRepository.findUserDetailByStatusAndUser(UserDetailStatusName.DEFAULT, userDetail1.getUser());
                userDetail.setStatus(UserDetailStatusName.UNDEFAULT);
                userDetailRepository.save(userDetail);

                userDetail1.setStatus(UserDetailStatusName.DEFAULT);
                userDetailRepository.save(userDetail1);
            }

            if (userDetailStatusName.equals(UserDetailStatusName.DELETED)) {


                if (orderRepository.findByUserDetail(userDetail1).size() > 0) {


                    if (userDetail1.getStatus().equals(UserDetailStatusName.DEFAULT)) {


                        UserDetail userDetail = userDetailRepository.findListUserDetailByStatusAndUser(UserDetailStatusName.UNDEFAULT, userDetail1.getUser()).get(0);
                        userDetail.setStatus(UserDetailStatusName.UNDEFAULT);
                        userDetailRepository.save(userDetail);


                    }

                    userDetail1.setStatus(UserDetailStatusName.DELETED);
                    userDetailRepository.save(userDetail1);

                } else {
                    if (userDetail1.getStatus().equals(UserDetailStatusName.DEFAULT)) {


                        UserDetail userDetail = userDetailRepository.findListUserDetailByStatusAndUser(UserDetailStatusName.UNDEFAULT, userDetail1.getUser()).get(0);
                        userDetail.setStatus(UserDetailStatusName.UNDEFAULT);
                        userDetailRepository.save(userDetail);


                    }
                    userDetailRepository.delete(userDetail1);
                }


            }


        } catch (Exception e) {
            throw new BadRequestException("invalid request" + e.getMessage());
        }
        return new UserDetailDto().toDto(userDetail1);
    }


    @Override
    public String deleteUserDetail(Integer detailId) throws ResourceNotFoundException {
        UserDetail userDetail = userDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("userdetail not found for this id: " + detailId));

        userDetailRepository.delete(userDetail);
        return "deleted";
    }


}
