package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CategoryDto;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.mapper.CategoryMapper;

import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;


    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) throws BadRequestException {
        Category category = mapper.toEntity(categoryDto);

        try {
            return mapper.toDto(repository.save(category));
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }


    }

    @Override
    public List<CategoryDto> retrieveCategories() {
        List<Category> categories =  repository.findAll();

        return mapper.toListDto(categories);
    }

    @Override
    public CategoryDto getCategoryById(int id) throws ResourceNotFoundException {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + id)));
    }

    @Override
    public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + categoryId));
        repository.delete(category);
    }

    @Override
    public void updateCategory(Integer categoryId, CategoryDto categoryDto) throws ResourceNotFoundException ,BadRequestException {


        Category existCategory = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + categoryId));


        existCategory.setDescription(categoryDto.getDescription());
        existCategory.setName(categoryDto.getName());
        existCategory.setImage(categoryDto.getImage());

        try {
            repository.save(existCategory);
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }


    }





}
