package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderRequestDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Order;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderService {
    public OrderDto saveOrder(OrderDto orderDto) throws ParseException;

    public List<OrderDto> retrieveOrders();

    public OrderDto getOrderById(int id) ;

    public void deleteOrder(Integer orderId) throws ParseException;

    public void updateOrder(Integer orderId, OrderDto  orderDto);

    public OrderDto createNewOrder(OrderRequestDto orderRequestDto);

    public List<OrderDto> getListOrderByUserId(int id) ;

    OrderDto createNewOrderFromCart(Integer userId);
}
