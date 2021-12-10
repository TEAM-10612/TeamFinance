package com.financialboard.service;

import com.financialboard.dto.CategoryDto;
import com.financialboard.model.category.Category;
import com.financialboard.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;



    public CategoryDto.SaveRequest request1(){
        return CategoryDto.SaveRequest.builder()
                .categoryName("category1")
                .parentCategory(null)

                .build();
    }

    public CategoryDto.SaveRequest request2(){
        return CategoryDto.SaveRequest.builder()
                .categoryName("category2")
                .parentCategory(request1().getCategoryName())
                .build();
    }

    @Test
    @DisplayName("카테고리 생성")
    void save_category()throws Exception{
        //given
        CategoryDto.SaveRequest request1 = request1();

        //when
        when(categoryRepository.existsByCategoryName(request1.getCategoryName()))
                .thenReturn(false);
        categoryService.createCategory(request1);

        //then
        Assertions.assertThat(request1.getCategoryName()).isEqualTo(request1().getCategoryName());
        Assertions.assertThat(request1.getParentCategory()).isEqualTo(null);

    }

    @Test
    @DisplayName("카테고리 수정")
    void update_category()throws Exception{
        //given
        Category category = request1().toEntity();
        Long id = category.getId();

        //when
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        categoryService.updateCategory(id,request2());

        //then
        Assertions.assertThat(category.getCategoryName()).isEqualTo(request2().getCategoryName());
    }

    @Test
    @DisplayName("카테고리 삭제")
    void delete_category()throws Exception{
        //given
        Category category = request1().toEntity();
        Long id = category.getId();

        //when
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        categoryService.deleteCategory(id);

        //then
        verify(categoryRepository,atLeastOnce()).deleteById(id);

    }

}