package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);

    Page<Order> findOrdersByStatus(OrderStatusName statusName, Pageable pageable);

    Page<Order> findOrdersByStatusAndOrderIdContaining( OrderStatusName statusName,String orderId, Pageable pageable);

    List<Order> findByUserDetail(UserDetail userDetail);

    @Query("select o from Order o where CONCAT(o.orderId, '') like %?1%")
    Page<Order> findOrdersByStatusAndOrderIdContaining (String orderId, Pageable pageable);
}
