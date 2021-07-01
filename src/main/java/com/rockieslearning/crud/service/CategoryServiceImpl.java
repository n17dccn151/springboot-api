package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaAuthException;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.mapper.CategoryMapper;
import com.rockieslearning.crud.mapper.UserMapper;
import com.rockieslearning.crud.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
public class CategoryServiceImpl implements  CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;


    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) throws FaBadRequestException, ParseException {
        Category category = mapper.toEntity(categoryDto);
        return mapper.toDto(repository.save(category));
    }

    @Override
    public List<CategoryDto> retrieveCategories() {
        List<Category> categories =  repository.findAll();

        return mapper.toListDto(categories);
    }

    @Override
    public CategoryDto getCategoryById(int id) throws FaResourceNotFoundException {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public void deleteCategory(Integer categoryId) throws ParseException {
        Category category = repository.findById(categoryId).get();
        repository.delete(category);
    }

    @Override
    public void updateCategory(Integer categoryId, CategoryDto categoryDto) {


        Category existCategory = repository.findById(categoryId).get();


        existCategory.setDescription(categoryDto.getDescription());
        existCategory.setName(categoryDto.getName());
        existCategory.setImage(categoryDto.getImage());


        repository.save(existCategory);
    }





}
