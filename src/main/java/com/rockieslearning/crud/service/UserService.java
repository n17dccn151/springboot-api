package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface UserService {

    public User saveUser(User user);

    public List<User> retrieveUsers();

    public User getUserById(int id);

    public void deleteUser(User user);



}
