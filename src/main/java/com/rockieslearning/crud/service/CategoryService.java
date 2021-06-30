package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface CategoryService {

    public Category saveCategory(Category category) ;

    public List<Category> retrieveCategories();

    public Category getCategoryById(int id) ;

    public void deleteCategory(Category category) ;


}
