package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.mapper.OrderMapper;
import com.rockieslearning.crud.mapper.UserMapper;
import com.rockieslearning.crud.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    OrderRepository repository;


    @Autowired
    private OrderMapper mapper;


    public OrderServiceImpl(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    @Override
    public OrderDto saveOrder(OrderDto orderDto) throws ParseException {
        Order order = mapper.toEntity(orderDto);
        return mapper.toDto(repository.save(order));
    }

    @Override
    public List<OrderDto> retrieveOrders() {
        List<Order> orders =  repository.findAll();
        return mapper.toListDto(orders);
    }

    @Override
    public OrderDto getOrderById(int id) {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public void deleteOrder(Integer orderId) throws ParseException {
        Order order = repository.findById(orderId).get();
        repository.delete(order);
    }

    @Override
    public void updateOrder(Integer orderId, OrderDto orderDto) {



        Order existOrder = repository.findById(orderId).get();

        existOrder.setPrice(orderDto.getPrice());
        existOrder.setAmount(orderDto.getAmount());
        existOrder.setStatus(orderDto.getStatus());

        //existOrder.setOrderFoods(orderDto.getOrderFoods());
    }


}
