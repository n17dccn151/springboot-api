package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
