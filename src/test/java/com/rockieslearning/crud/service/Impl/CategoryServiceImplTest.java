package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.service.Impl.CategoryServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {


    @Mock
    private CategoryRepository repository;


    @InjectMocks
    private CategoryServiceImpl categoryService;

//    @Rule
//    public final ResourceNotFoundException exception = new ResourceNotFoundException("");

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    private Category category;
    private List<Category> categories;
    private CategoryDto categoryDto;
    private List<CategoryDto> categoryDtos;


    @BeforeEach
    public void setUp() {

        categories = new ArrayList<>();
        category = new Category(1, "name", "description", "image", null);
        categories.add(category);

        categoryDto = new CategoryDto().toDto(category);
        categoryDtos = new CategoryDto().toListDto(categories);

    }


    @AfterEach
    public void tearDown() {
        category = null;
        categoryDto = null;

        categories = null;
        categoryDtos = null;
    }


    @Test
    public void whenGivenCategoryDto_shouldAddCategory() {

        when(repository.save(any(Category.class))).thenReturn(category);

        categoryService.saveCategory(categoryDto);

        verify(repository, times(1)).save(any());

    }

    @Test
    public void shouldReturnAllCategoryDto() {


        when(repository.findAll()).thenReturn(categories);

        List<CategoryDto> categoryDtos1 = categoryService.retrieveCategories();

//        assertEquals(categoryDtos1.size(),categoryDtos.size());

        assertThat(categoryDtos).usingRecursiveComparison().isEqualTo(categoryDtos1);

        verify(repository, times(1)).findAll();
    }


    @Test
    public void whenGivenId_shouldReturnCategory_ifFound() {
        when(repository.findById(category.getCategoryId())).thenReturn(Optional.ofNullable(category));

        CategoryDto categoryDto1 = categoryService.getCategoryById(category.getCategoryId());

        assertThat(categoryDto1).usingRecursiveComparison().isEqualTo(categoryDto);
    }


    @Test()
    public void whenGivenId_shouldDeleteCategory_ifFound() {

        when(repository.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        categoryService.deleteCategory(category.getCategoryId());

        verify(repository).delete(category);

    }

    @Test()
    public void whenGivenId_shouldNotDeleteCategory_ifNotFound() {

        given(repository.findById(category.getCategoryId())).willReturn(Optional.ofNullable(null));

        try {
            categoryService.deleteCategory(category.getCategoryId());
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }


    }

    @Test
    public void whenGivenId_shouldUpdateCategory_ifFound() {

        given(repository.findById(category.getCategoryId())).willReturn(Optional.of(category));

        when(repository.save(any(Category.class))).thenReturn(category);

        categoryService.updateCategory(category.getCategoryId(), categoryDto);

        verify(repository).save(category);

        verify(repository).findById(category.getCategoryId());
    }


    @Test
    public void whenGivenId_shouldUpdateCategory_ifNotFound() {

        given(repository.findById(category.getCategoryId())).willReturn(Optional.ofNullable(null));

        try {
            categoryService.updateCategory(category.getCategoryId(), categoryDto);
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }
    }





}



