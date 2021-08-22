package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.RoleName;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.entity.UserDetailStatusName;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.request.SignupRequest;
import com.rockieslearning.crud.payload.request.SignupRequestV2;
import com.rockieslearning.crud.payload.response.JwtResponse;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface UserService {

    public UserDto saveUser(UserDto userDto) throws BadRequestException;

    public List<UserDto> retrieveUsers();

    public List<UserDto> retrieveUsers(RoleName role, Pageable pageable) throws BadRequestException;


    public List<UserDto> getUserByPhone(RoleName role, String phone, Pageable pageable) throws BadRequestException;


    public UserDto getUserById(Long id) throws ResourceNotFoundException;

    public String deleteUser(Long userId) throws ResourceNotFoundException;

    public UserDto updateUser(Long userId, UserDto userDto) throws ResourceNotFoundException, BadRequestException;


    public JwtResponse signIn(LoginRequest loginRequest);

    public UserDto signUp(SignupRequest signupRequest);

    public UserDto signUpV2(SignupRequestV2 signupRequest);


    public List<UserDetailDto> getListDetailByUserId(Long userId) throws ResourceNotFoundException;

    public UserDetailDto getUserDetail(Integer id) throws ResourceNotFoundException;

    public UserDetailDto saveUserDetail(Long userId, UserDetailDto userDetailDto) throws BadRequestException;

    public UserDetailDto updateUserDetail(Integer detailId, UserDetailDto userDetailDto) throws BadRequestException;

    public UserDetailDto updateUserDetailStatus(Integer detailId, UserDetailStatusName userDetailStatusName) throws BadRequestException;


    public String deleteUserDetail(Integer detailId) throws ResourceNotFoundException;
}
