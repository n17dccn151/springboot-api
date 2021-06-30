package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    OrderRepository repository;

    @Override
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> retrieveOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrder(Order order) {
        repository.delete(order);
    }
}
