package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.FoodComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodCommentRepository extends JpaRepository<FoodComment, Integer> {
}
