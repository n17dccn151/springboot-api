package com.rockieslearning.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TanVOD on Jun, 2021
 */

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CategoryService categoryService;


    private static ObjectMapper mapper = new ObjectMapper();


    CategoryDto mockCategoryDto = new CategoryDto(1, "name", "description", "image url");

    List<CategoryDto> mockCategoryDtos = new ArrayList<>();


    @Test
    public void testGetAllCategory() throws Exception {

        mockCategoryDtos.add(mockCategoryDto);

        Mockito.when(categoryService.retrieveCategories()).thenReturn(mockCategoryDtos);


        mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$[0].description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$[0].image", Matchers.equalTo("image url")));

    }


    @Test
    public void testGetCategoryById() throws Exception {

        Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyInt())).thenReturn(mockCategoryDto);


        mockMvc.perform(get("/api/categories/" + "1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.image", Matchers.equalTo("image url")));


    }


    @Test
    public void testAddCategory() throws Exception {


        Mockito.when(categoryService.saveCategory(ArgumentMatchers.any())).thenReturn(mockCategoryDto);


        String json = mapper.writeValueAsString(mockCategoryDto);

        mockMvc.perform(post("/api/categories/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.image", Matchers.equalTo("image url")));


    }


    @Test
    public void testUpdateCategory() throws Exception {

        Mockito.when(categoryService.updateCategory(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(mockCategoryDto);


        String json = mapper.writeValueAsString(mockCategoryDto);

        mockMvc.perform(put("/api/categories/{categoryId}", 1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.image", Matchers.equalTo("image url")));


    }


    @Test
    public void testDeleteCategory() throws Exception {


        Mockito.when(categoryService.deleteCategory(ArgumentMatchers.anyInt())).thenReturn("deleted");


        MvcResult requestResult = mockMvc.perform(delete("/api/categories/{categoryId}", 1)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)))
                .andReturn();


    }
}
