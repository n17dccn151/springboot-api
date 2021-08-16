package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByCategory(Category category);

    List<Food> findByCategory(Category category , Pageable pageable);

    Page<Food> findByNameContaining(String name, Pageable pageable);

    List<Food> findByNameContaining(String name, Sort sort);

    @Query("select o from Food o where LOWER(o.name) like %?1%")
    List<Food> findByNameContaining(String name);

    Food getFoodByName(String name);
}
