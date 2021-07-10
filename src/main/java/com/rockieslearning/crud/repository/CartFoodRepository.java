package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Cart;
import com.rockieslearning.crud.entity.CartFood;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface CartFoodRepository extends JpaRepository<CartFood, Integer> {
    List<CartFood> findByCart(Cart cart);

    CartFood findCartFoodByFoodAndCart(Food food, Cart cart);

    void deleteAllByCart(Cart cart);


}
