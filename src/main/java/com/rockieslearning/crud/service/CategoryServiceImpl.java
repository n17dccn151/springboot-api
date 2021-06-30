package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaAuthException;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
public class CategoryServiceImpl implements  CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public Category saveCategory(Category category) throws FaBadRequestException {
        return repository.save(category);
    }

    @Override
    public List<Category> retrieveCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(int id) throws FaResourceNotFoundException {
        return repository.findById(id).get();
    }

    @Override
    public void deleteCategory(Category category) throws FaResourceNotFoundException {
        repository.delete(category);
    }


}
