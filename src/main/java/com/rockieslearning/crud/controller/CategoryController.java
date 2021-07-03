package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
    public ResponseEntity<List<CategoryDto>> getAllCategory(){

        List<CategoryDto> categories = categoryService.retrieveCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(HttpServletRequest request,
                                            @PathVariable("categoryId") Integer categoryId){


        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<CategoryDto> addCategory(HttpServletRequest request,
                                                   @RequestBody CategoryDto categoryDto) throws ParseException {


        CategoryDto saveCategory =  categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }




    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody CategoryDto categoryDto){

        categoryService.updateCategory(categoryId, categoryDto);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
}


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId) throws ParseException {

        categoryService.deleteCategory(categoryId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
