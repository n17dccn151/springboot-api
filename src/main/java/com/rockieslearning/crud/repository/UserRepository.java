package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findUserByPhone(String phone);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);



}
