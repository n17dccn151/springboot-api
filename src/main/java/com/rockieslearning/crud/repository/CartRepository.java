package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
