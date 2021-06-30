package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Order;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderService {
    public Order saveOrder(Order order) ;

    public List<Order> retrieveOrders();

    public Order getOrderById(int id) ;

    public void deleteOrder(Order order) ;

}
