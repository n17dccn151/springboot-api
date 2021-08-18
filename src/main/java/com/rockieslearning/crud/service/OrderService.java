package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderFoodDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.OrderStatusName;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface OrderService {
    public OrderDto saveOrder(OrderDto orderDto) throws BadRequestException;

    public List<OrderDto> retrieveOrders();

    public List<OrderDto> retrieveOrders(OrderStatusName statusName, Pageable pageable);

    public OrderDto getOrderById(int id) throws ResourceNotFoundException;

    public List<OrderDto> retrieveOrderById(OrderStatusName statusName,String id ,Pageable pageable) throws ResourceNotFoundException;

    public List<OrderDto> retrieveOrderById(String id ,Pageable pageable) throws ResourceNotFoundException;

    public String deleteOrder(Integer orderId) throws ResourceNotFoundException;

    public OrderDto updateOrder(Integer orderId, OrderDto orderDto) throws ResourceNotFoundException, BadRequestException;

    public OrderDto createNewOrder(Long userId, OrderDto orderDto) throws BadRequestException;

    public List<OrderDto> getListOrderByUserId(Long id);

    OrderDto createNewOrderFromCart(Long userId);

    public OrderDto createNewOrderFromBot(Long userId, List<BigDecimal> numbers, List<String> names);

}
