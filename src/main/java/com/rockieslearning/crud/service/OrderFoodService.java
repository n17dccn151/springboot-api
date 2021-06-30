package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.OrderFood;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderFoodService {
    public OrderFood saveOrderFood(OrderFood orderFood);

    public List<OrderFood> retrieveOrderFoods();

    public OrderFood getOrderFoodById(int id);

    public void deleteOrderFood(OrderFood orderFood);
}
