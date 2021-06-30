package com.rockieslearning.crud.repository;


import com.rockieslearning.crud.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
