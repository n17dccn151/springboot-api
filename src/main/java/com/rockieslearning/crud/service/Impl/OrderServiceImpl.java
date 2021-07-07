package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.*;
import com.rockieslearning.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.schema.Enums;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    OrderFoodRepository orderFoodRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartFoodRepository cartFoodRepository;


    public OrderServiceImpl(OrderRepository repository, UserRepository userRepository, FoodRepository foodRepository, OrderFoodRepository orderFoodRepository, CartRepository cartRepository, CartFoodRepository cartFoodRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.orderFoodRepository = orderFoodRepository;
        this.cartRepository = cartRepository;
        this.cartFoodRepository = cartFoodRepository;
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) throws BadRequestException {
        Order order = new OrderDto().toEntity(orderDto);

        try {
            return new OrderDto().toDto(repository.save(order));
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }

    }

    @Override
    public List<OrderDto> retrieveOrders() {
        List<Order> orders =  repository.findAll();
        return new OrderDto().toListDto(orders);
    }

    @Override
    public OrderDto getOrderById(int id) throws ResourceNotFoundException{
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));

        return new OrderDto().toDto(order);
    }

    @Override
    public void deleteOrder(Integer orderId) throws ResourceNotFoundException {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + orderId));
        repository.delete(order);
    }

    @Override
    public OrderDto updateOrder(Integer orderId, OrderDto orderDto) throws  ResourceNotFoundException {

        Order existOrder = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + orderId));

//        existOrder.setPrice(orderDto.getPrice());
//        existOrder.setAmount(orderDto.getAmount());

        for (Object s : OrderStatusName.values())
        {
            if (orderDto.getStatus().equals(s.toString()))
            {
                existOrder.setStatus(s.toString());
                return new OrderDto().toDto(repository.save(existOrder));
            }
        }

        throw new BadRequestException("Invalid request");



    }

    @Override
    public OrderDto createNewOrder(Long userId, OrderDto orderDto) throws ResourceNotFoundException, BadRequestException {

/*{
        "userId": 7,
        "orderFoods": [
            {
                "id": 2,
                "amount": 456
            }
        ]
}*/
        Order order =  new Order();
        order.setStatus("ORDERED");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " +userId));
        order.setUser(user);
        Order saveOrder;


        try {
             saveOrder  = repository.save(order);


            orderDto.getOrderFoods().forEach(e->{

                Food food = new Food();
                food = foodRepository.getById(e.getId());

                OrderFood orderFood  = new OrderFood();
                orderFood.setOrder(saveOrder);
                orderFood.setFood(food);
                orderFood.setAmount(e.getAmount());
                orderFood.setPrice(food.getPrice());
                orderFoodRepository.save(orderFood);

            });
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }

        return new OrderDto().toDto(saveOrder);
    }

    @Override
    public List<OrderDto> getListOrderByUserId(Long id) {
        User user = userRepository.getById(id);
        List<Order> orders = repository.findByUser(user);

        return new OrderDto().toListDto(orders);
    }

    @Override
    public OrderDto createNewOrderFromCart(Long userId) {

        User user = userRepository.getById(userId);
        Cart cart = cartRepository.findByUser(user);

        Order order =  new Order();
        order.setStatus("ORDERED");
        order.setUser(user);


        Order saveOrder = repository.save(order);
        List<CartFood> cartFoods = cartFoodRepository.findByCart(cart);


        cartFoods.forEach(e->{
            Food food = new Food();
            food = foodRepository.getById(e.getFood().getFoodId());

            OrderFood orderFood  = new OrderFood();
            orderFood.setOrder(saveOrder);
            orderFood.setFood(food);
            orderFood.setAmount(e.getAmount());
            orderFood.setPrice(food.getPrice());
            orderFoodRepository.save(orderFood);
        });

        cartFoodRepository.deleteAllByCart(cart);

        return new OrderDto().toDto(repository.getById(saveOrder.getOrderId()));
    }


}
