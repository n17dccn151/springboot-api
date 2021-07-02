package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Cart;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
