package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.request.SignupRequest;
import com.rockieslearning.crud.payload.response.JwtResponse;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface UserService {

    public UserDto saveUser(UserDto userDto) throws BadRequestException;

    public List<UserDto> retrieveUsers();

    public UserDto getUserById(Long id)  throws ResourceNotFoundException;

    public void deleteUser(Long userId) throws ResourceNotFoundException;

    public UserDto updateUser(Long userId, UserDto userDto)throws ResourceNotFoundException, BadRequestException;


    public JwtResponse signIn(LoginRequest loginRequest);

    public UserDto signUp(SignupRequest signupRequest);


    public List<UserDetailDto> getListDetailByUserId(Long userId) throws ResourceNotFoundException;

    public UserDetailDto getUserDetail(Integer id) throws ResourceNotFoundException;

    public UserDetailDto saveUserDetail(Long userId, UserDetailDto userDetailDto) throws BadRequestException;

    public UserDetailDto updateUserDetail(Integer detailId, UserDetailDto userDetailDto) throws BadRequestException;

    public void deleteUserDetail(Integer detailId) throws ResourceNotFoundException;
}
