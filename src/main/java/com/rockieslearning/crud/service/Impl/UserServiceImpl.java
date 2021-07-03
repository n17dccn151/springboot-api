package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.exception.AuthException;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.mapper.UserDetailMapper;
import com.rockieslearning.crud.mapper.UserMapper;
import com.rockieslearning.crud.repository.UserDetailRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDetailRepository userDetailRepository ;


    @Autowired
    private UserMapper mapper;


    @Autowired
    private UserDetailMapper userDetailMapper;

    public UserServiceImpl(UserRepository repository, UserDetailRepository userDetailRepository, UserMapper mapper, UserDetailMapper userDetailMapper) {
        this.repository = repository;
        this.userDetailRepository = userDetailRepository;
        this.mapper = mapper;
        this.userDetailMapper = userDetailMapper;
    }

    @Override
    public List<UserDto> retrieveUsers() {

        List<User> users =  repository.findAll();

        return mapper.toListDto(users);
    }

    @Override
    public UserDto getUserById(int id) throws ResourceNotFoundException{

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + id));
        return mapper.toDto(user);
    }

    @Override
    public void deleteUser(Integer userId) throws ResourceNotFoundException {


        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));
        repository.delete(user);
    }

    @Override
    public void updateUser(Integer userId, UserDto userDto) throws ResourceNotFoundException, BadRequestException{


        User existUser = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));


        existUser.setEmail(userDto.getEmail());
        existUser.setPhone(userDto.getPhone());
        existUser.setPassword(userDto.getPassword());
        //existUser.setRoles(userDto.getRoles());

        try {
            repository.save(existUser);
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }
    }

    @Override
    public List<UserDetail> getListDetailByUserId(Integer userId) throws ResourceNotFoundException {
        List<UserDetail> userDetails = new ArrayList<>();

        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));

        userDetails = userDetailRepository.findByUser(user);
        return  userDetails;
    }




    @Override
    public UserDto saveUser(UserDto userDto) throws BadRequestException {
        User user = mapper.toEntity(userDto);
        User saveUser = new User();

        try {
            saveUser = repository.save(user);
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }

        return mapper.toDto(saveUser);
    }





}
