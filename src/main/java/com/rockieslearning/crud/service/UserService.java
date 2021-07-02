package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
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

    public UserDto getUserById(int id)  throws ResourceNotFoundException;

    public void deleteUser(Integer userId) throws ResourceNotFoundException;

    public void updateUser(Integer userId, UserDto userDto)throws ResourceNotFoundException, BadRequestException;

    public List<UserDetail> getListDetailByUserId(Integer userId) throws ResourceNotFoundException;

    User validateUser(UserDto userDto);

    UserDto registerUser(UserDto userDto);
}
