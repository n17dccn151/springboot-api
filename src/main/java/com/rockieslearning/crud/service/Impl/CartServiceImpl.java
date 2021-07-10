package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.entity.Cart;
import com.rockieslearning.crud.entity.CartFood;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.CartFoodRepository;
import com.rockieslearning.crud.repository.CartRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public CartServiceImpl(CartRepository repository, UserRepository userRepository, CartRepository cartRepository, CartFoodRepository cartFoodRepository, FoodRepository foodRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartFoodRepository = cartFoodRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public CartDto saveCart(CartDto CartDto) throws BadRequestException {
        Cart Cart = new CartDto().toEntity(CartDto);

        try {
            return new CartDto().toDto(repository.save(Cart));
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");

        }

    }

    @Override
    public List<CartDto> retrieveCarts() {
        List<Cart> Carts = repository.findAll();
        return new CartDto().toListDto(Carts);
    }

    @Override
    public CartDto getCartById(int id) throws ResourceNotFoundException {
        Cart cart = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id: " + id));
        return new CartDto().toDto(cart);
    }

    @Override
    public void deleteCart(Integer CartId) throws ResourceNotFoundException {
        Cart cart = repository.findById(CartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id "));
        repository.delete(cart);
    }


    @Override
    public CartDto getCartByUserId(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + id));
        ;

        Cart cart = repository.findByUser(user);
        if (cart == null)
            throw new ResourceNotFoundException("Cart not found ");

        return new CartDto().toDto(cart);
    }


    @Override
    public CartDto updateCart(Long userId, CartFoodDto cartFoodDto) {
        User user = userRepository.getById(userId);
        Cart cart = cartRepository.findByUser(user);
        Food food = foodRepository.getById(cartFoodDto.getId());


        if (cart == null) {
            cart = repository.save(new Cart(user));
        }

        CartFood cartFood = cartFoodRepository.findCartFoodByFoodAndCart(food, cart);


        if (cartFood == null) {
            cartFoodRepository.save(new CartFood(cart, food, cartFoodDto.getAmount()));
        } else {

            if (cartFoodDto.getAmount() == 0) {
                cartFoodRepository.deleteById(cartFood.getId());

            } else {
                cartFood.setAmount(cartFoodDto.getAmount());
                cartFoodRepository.save(cartFood);

            }
        }

        Cart s = repository.findById(cart.getCartId()).orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id: "));
        return new CartDto().toDto(s);
    }

}
