package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderRequestDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.*;
import com.rockieslearning.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void updateOrder(Integer orderId, OrderDto orderDto) throws  ResourceNotFoundException {

        Order existOrder = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + orderId));

//        existOrder.setPrice(orderDto.getPrice());
//        existOrder.setAmount(orderDto.getAmount());
        existOrder.setStatus(orderDto.getStatus());

        //existOrder.setOrderFoods(orderDto.getOrderFoods());
    }

    @Override
    public OrderDto createNewOrder(OrderRequestDto orderRequestDto) throws ResourceNotFoundException, BadRequestException {

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

        for (Object s : OrderStatusName.values())
        {
            if (orderRequestDto.getStatus().equals(s.toString()))
            {
                order.setStatus(s.toString());
            }
        }



        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + orderRequestDto.getUserId()));
        order.setUser(user);
        Order saveOrder;
        try {
             saveOrder  = repository.save(order);


            orderRequestDto.getOrderFoods().forEach(e->{

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
        order.setStatus("Ordered");
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

        cartRepository.delete(cart);

        return new OrderDto().toDto(saveOrder);
    }


}
