package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaAuthException;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.mapper.UserMapper;
import com.rockieslearning.crud.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;


    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> retrieveUsers() {

        List<User> users =  repository.findAll();

        return mapper.toListDto(users);
    }

    @Override
    public UserDto getUserById(int id) {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public void deleteUser(Integer userId) throws FaResourceNotFoundException, ParseException {


        User user = repository.findById(userId).get();
        repository.delete(user);
    }

    @Override
    public void updateUser(Integer userId, UserDto userDto) {


        User existUser = repository.findById(userId).get();

        existUser.setEmail(userDto.getEmail());
        existUser.setPhone(userDto.getPhone());
        existUser.setPassword(userDto.getPassword());
        existUser.setRoles(userDto.getRoles());

        repository.save(existUser);
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws FaBadRequestException, ParseException {
        User user = mapper.toEntity(userDto);
        return mapper.toDto(repository.save(user));
    }





}
