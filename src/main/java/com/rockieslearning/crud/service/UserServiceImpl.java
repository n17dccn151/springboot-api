package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaAuthException;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;



    @Override
    public List<User> retrieveUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteUser(User user) throws FaResourceNotFoundException {
        repository.delete(user);
    }

    @Override
    public User saveUser(User user) throws FaBadRequestException {
        return repository.save(user);
    }


}
