package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
