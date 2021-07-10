package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderService {
    public OrderDto saveOrder(OrderDto orderDto) throws BadRequestException;

    public List<OrderDto> retrieveOrders();

    public OrderDto getOrderById(int id) throws ResourceNotFoundException;

    public void deleteOrder(Integer orderId) throws ResourceNotFoundException;

    public OrderDto updateOrder(Integer orderId, OrderDto orderDto) throws ResourceNotFoundException, BadRequestException;

    public OrderDto createNewOrder(Long userId, OrderDto orderDto) throws BadRequestException;

    public List<OrderDto> getListOrderByUserId(Long id);

    OrderDto createNewOrderFromCart(Long userId);
}
