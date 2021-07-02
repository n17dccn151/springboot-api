package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.entity.Cart;
import com.rockieslearning.crud.entity.CartFood;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.mapper.CartMapper;
import com.rockieslearning.crud.repository.CartFoodRepository;
import com.rockieslearning.crud.repository.CartRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartFoodRepository cartFoodRepository;

    @Autowired
    FoodRepository foodRepository;



    @Autowired
    private CartMapper mapper;


    public CartServiceImpl(CartRepository repository, CartMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    @Override
    public CartDto saveCart(CartDto CartDto) throws ParseException {
        Cart Cart = mapper.toEntity(CartDto);
        return mapper.toDto(repository.save(Cart));
    }

    @Override
    public List<CartDto> retrieveCarts() {
        List<Cart> Carts =  repository.findAll();
        return mapper.toListDto(Carts);
    }

    @Override
    public CartDto getCartById(int id) {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public void deleteCart(Integer CartId) throws ParseException {
        Cart Cart = repository.findById(CartId).get();
        repository.delete(Cart);
    }



    @Override
    public CartDto getCartByUserId(int id) {
        User user = userRepository.getById(id);

        Cart cart = repository.findByUser(user);

        return mapper.toDto(cart);
    }

    @Override
    public CartDto addToCart(Integer userId, Integer foodId) {
        User user = userRepository.getById(userId);
        Cart cart = cartRepository.findByUser(user);
        Food food = foodRepository.getById(foodId);



        if(cart==null){
            cart = repository.save(new Cart(user));
        }


        CartFood cartFood = cartFoodRepository.findCartFoodByFoodAndCart(food, cart);
        if(cartFood == null){
            cartFoodRepository.save(new CartFood(cart, food, 1));
        }else{
            cartFood.setAmount(cartFood.getAmount()+1);
            cartFoodRepository.save(cartFood);
        }


        return mapper.toDto(repository.getById(cart.getCartId()));
    }


    @Override
    public CartDto updateCart(Integer userId, Integer foodId, Integer qty) {
        User user = userRepository.getById(userId);
        Cart cart = cartRepository.findByUser(user);
        Food food = foodRepository.getById(foodId);


        if(cart==null){
            cart = repository.save(new Cart(user));
        }


        CartFood cartFood = cartFoodRepository.findCartFoodByFoodAndCart(food, cart);
        if(cartFood == null){
            cartFoodRepository.save(new CartFood(cart, food, qty));
        }else{
            cartFood.setAmount(cartFood.getAmount()+qty);
            cartFoodRepository.save(cartFood);
        }


        return mapper.toDto(repository.getById(cart.getCartId()));
    }

}
