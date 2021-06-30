package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanVOD on Jun, 2021
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory(){

        List<Category> categories = new ArrayList<>();
        categories = categoryService.retrieveCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest request,
                                            @PathVariable("categoryId") Integer categoryId){


        Category category = categoryService.getCategoryById(categoryId);
        if(category==null){
            throw new FaResourceNotFoundException("Category not found");
        }
        return new ResponseEntity<>(category,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request, @RequestBody Category category){


        Category categoryResult =  categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryResult, HttpStatus.CREATED);
    }




    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody Category category){
        Category existCategory = categoryService.getCategoryById(categoryId);
        if(existCategory==null){
            throw new FaResourceNotFoundException("Category not found");
        }
        existCategory.setDescription(category.getDescription());
        existCategory.setName(category.getName());
        existCategory.setImage(category.getImage());
        existCategory.setFoods(category.getFoods());


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
}


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId){


        Category category = categoryService.getCategoryById(categoryId);
        if(category==null){
            throw new FaResourceNotFoundException("Category not found");
        }


        categoryService.deleteCategory(category);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
