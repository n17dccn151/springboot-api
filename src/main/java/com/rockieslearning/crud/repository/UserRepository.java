package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Role;
import com.rockieslearning.crud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findUserByPhone(String phone);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Page<User> findByPhoneContaining(String phone, Pageable pageable);

    List<User> findByPhoneContaining(String phone, Sort sort);

    Page<User>findUserByRoles(Role role, Pageable pageable);

    Page<User> findUserByRolesAndPhoneContaining(Role role, String phone, Pageable pageable);

}
