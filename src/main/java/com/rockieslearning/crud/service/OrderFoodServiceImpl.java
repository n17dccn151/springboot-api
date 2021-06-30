package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.OrderFood;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
@Service
public class OrderFoodServiceImpl implements OrderFoodService{

    @Autowired
    private OrderFoodRepository repository;

    @Override
    public OrderFood saveOrderFood(OrderFood orderFood) {
        return repository.save(orderFood);
    }

    @Override
    public List<OrderFood> retrieveOrderFoods() {
        return repository.findAll();
    }

    @Override
    public OrderFood getOrderFoodById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteOrderFood(OrderFood orderFood) {
        repository.delete(orderFood);
    }
}
