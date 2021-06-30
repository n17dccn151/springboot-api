package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
