package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderFoodDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.*;
import com.rockieslearning.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.schema.Enums;

import java.text.ParseException;
import java.util.ArrayList;
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
    OrderRepository orderRepository;

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

    @Autowired
    UserDetailRepository userDetailRepository;

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
        List<Order> orders = repository.findAll();
        return new OrderDto().toListDto(orders);
    }

    @Override
    public List<OrderDto> retrieveOrders(OrderStatusName statusName, Pageable pageable) {
        List<Order> orders =  new ArrayList<>();

        Page<Order> orderPage;

        try {
            orderPage = repository.findOrdersByStatus(statusName, pageable);
        }catch (Exception e){
            throw new BadRequestException("invalid Request " + e.getMessage());
        }

        orders = orderPage.getContent();
        return new OrderDto().toListDto(orders);
    }

    @Override
    public OrderDto getOrderById(int id) throws ResourceNotFoundException {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));

        return new OrderDto().toDto(order);
    }

    @Override
    public List<OrderDto> retrieveOrderById(OrderStatusName statusName, String id, Pageable pageable) throws ResourceNotFoundException {
        List<Order> orders =  new ArrayList<>();


        Page<Order> orderPage;
        try {
            System.out.println("____________+++++++++++==-");
            orderPage = repository.findOrdersByStatusAndOrderIdContaining( statusName, id,pageable);
        }catch (Exception e){
            throw new BadRequestException("invalid Request " + e.getMessage());
        }

        orders = orderPage.getContent();
        return new OrderDto().toListDto(orders);
    }

    @Override
    public List<OrderDto> retrieveOrderById(String id, Pageable pageable) throws ResourceNotFoundException {
        List<Order> orders =  new ArrayList<>();


        Page<Order> orderPage;
        try {
            System.out.println("____________+++++++++++==-");
            orderPage = repository.findOrdersByStatusAndOrderIdContaining(id,pageable);
        }catch (Exception e){
            throw new BadRequestException("invalid Request " + e.getMessage());
        }

        orders = orderPage.getContent();
        return new OrderDto().toListDto(orders);
    }

    @Override
    public String deleteOrder(Integer orderId) throws ResourceNotFoundException {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + orderId));
        repository.delete(order);
        return "deleted";
    }

    @Override
    public OrderDto updateOrder(Integer orderId, OrderDto orderDto) throws ResourceNotFoundException {

        Order existOrder = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + orderId));

//        existOrder.setPrice(orderDto.getPrice());
//        existOrder.setAmount(orderDto.getAmount());


        //chane swich case
        for (OrderStatusName s : OrderStatusName.values()) {
            if (orderDto.getStatus().equals(s) && orderDto.getStatus().equals(existOrder.getStatus()) == false) {
                existOrder.setStatus(s);

                if (s.toString().equals(OrderStatusName.CANCELLED.toString())) {
                    existOrder.getOrderFoods().forEach(e -> {

                        Food food = new Food();
                        food = foodRepository.findById(e.getFood().getFoodId()).orElseThrow(() -> new ResourceNotFoundException("not found for this id: "));
                        food.setQuantity(food.getQuantity() + e.getAmount());
                        foodRepository.save(food);

                    });
                }

                if (s.toString().equals(OrderStatusName.ORDERED.toString())) {
                    existOrder.getOrderFoods().forEach(e -> {

                        Food food = new Food();
                        food = foodRepository.findById(e.getFood().getFoodId()).orElseThrow(() -> new ResourceNotFoundException("not found for this id: "));
                        food.setQuantity(food.getQuantity() - e.getAmount());
                        foodRepository.save(food);

                    });
                }

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


        UserDetail userDetail = userDetailRepository.findById(orderDto.getDetailId()).orElseThrow(() -> new ResourceNotFoundException("detail not found for this id: "+ orderDto.getDetailId()));
        Order order = new Order();
        order.setStatus(OrderStatusName.ORDERED);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        order.setUser(user);
        order.setUserDetail(userDetail);
        Order saveOrder;
        Cart cart = cartRepository.findByUser(user);

        try {
            saveOrder = repository.save(order);


            orderDto.getOrderFoods().forEach(e -> {

                Food food = new Food();
                food = foodRepository.findById(e.getId()).orElseThrow(() -> new ResourceNotFoundException("not found for this id: "));

                if(food.getQuantity()< e.getAmount()){
                    food.setQuantity(food.getQuantity());
                }

                food.setQuantity(food.getQuantity() - e.getAmount());
                foodRepository.save(food);

                OrderFood orderFood = new OrderFood();
                orderFood.setOrder(saveOrder);
                orderFood.setFood(food);

                if(e.getAmount()>food.getQuantity()){
                    orderFood.setAmount(food.getQuantity());
                }


                orderFood.setAmount(e.getAmount());
                orderFood.setPrice(food.getPrice());
                orderFoodRepository.save(orderFood);

                cartFoodRepository.deleteByCartAndFood(cart,food);

            });


        } catch (Exception e) {
            throw new BadRequestException("invalid Request");
        }

        Order order1 = orderRepository.findById(saveOrder.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("not found for this id: "));
        return new OrderDto().toDto(order1);
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

        Order order = new Order();
        order.setStatus(OrderStatusName.ORDERED);
        order.setUser(user);


        Order saveOrder = repository.save(order);
        List<CartFood> cartFoods = cartFoodRepository.findByCart(cart);


        cartFoods.forEach(e -> {
            Food food = new Food();
            food = foodRepository.getById(e.getFood().getFoodId());

            OrderFood orderFood = new OrderFood();
            orderFood.setOrder(saveOrder);
            orderFood.setFood(food);
            if(e.getAmount()>food.getQuantity()){
                orderFood.setAmount(food.getQuantity());
            }
            orderFood.setAmount(e.getAmount());
            orderFood.setPrice(food.getPrice());



            orderFoodRepository.save(orderFood);


        });

        cartFoodRepository.deleteAllByCart(cart);

        return new OrderDto().toDto(orderRepository.getById(saveOrder.getOrderId()));
    }


}
