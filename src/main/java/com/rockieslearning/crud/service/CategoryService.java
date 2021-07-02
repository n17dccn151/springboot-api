package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CategoryDto;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface CategoryService {

    public CategoryDto saveCategory(CategoryDto categoryDto) throws ParseException;

    public List<CategoryDto> retrieveCategories();

    public CategoryDto getCategoryById(int id) ;

    public void deleteCategory(Integer categoryId) throws ParseException;

    public void updateCategory(Integer categoryId, CategoryDto categoryDto);


}
