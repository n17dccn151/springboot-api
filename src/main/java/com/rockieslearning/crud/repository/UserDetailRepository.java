package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.entity.UserDetailStatusName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    List<UserDetail> findByUser(User user);

    UserDetail findUserDetailByStatusAndUser(UserDetailStatusName statusName, User user);

    List<UserDetail> findListUserDetailByStatusAndUser(UserDetailStatusName statusName, User user);
}
