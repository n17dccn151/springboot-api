package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface UserService {

    public UserDto saveUser(UserDto userDto) throws ParseException;

    public List<UserDto> retrieveUsers();

    public UserDto getUserById(int id);

    public void deleteUser(Integer userId) throws ParseException;

    public void updateUser(Integer userId, UserDto userDto);

}
