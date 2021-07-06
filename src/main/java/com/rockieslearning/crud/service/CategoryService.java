package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CategoryDto;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public interface CategoryService {

    public CategoryDto saveCategory(CategoryDto categoryDto) throws BadRequestException;

    public List<CategoryDto> retrieveCategories();

    public CategoryDto getCategoryById(int id)  throws ResourceNotFoundException;

    public void deleteCategory(Integer categoryId)  throws ResourceNotFoundException;

    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) throws ResourceNotFoundException ,BadRequestException ;


}
