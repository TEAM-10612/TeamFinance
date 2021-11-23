package com.financialboard.service;

import com.financialboard.dto.CategoryDto;
import com.financialboard.dto.CategoryDto.CategoryInfo;
import com.financialboard.exception.category.NotFoundCategoryException;
import com.financialboard.model.category.Category;
import com.financialboard.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CategoryRepository categoryRepository;

    private CategoryInfo createCategoryInfo(String testBranch,String testCode,String testName){
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setBranch(testBranch);
        categoryInfo.setCode(testCode);
        categoryInfo.setName(testName);
        return categoryInfo;
    }

    private Category findCategory(Long saveId){
        return categoryRepository.findById(saveId).orElseThrow(IllegalArgumentException::new);
    }
    
    @Test
    void 카테고리_저장()throws Exception{
        //given
        CategoryInfo   categoryInfo = createCategoryInfo("TestBranch","TestCode","TestName");
        Long saveId = categoryService.saveCategory(categoryInfo);

        //when
        Category category = findCategory(saveId);

        //then
        assertThat(category.getCode()).isEqualTo("TestCode");
    
    }

    @Test
    void 카테고리_삭제 ()throws Exception{
        //given
        CategoryInfo categoryInfo = createCategoryInfo("TestBranch","TestCode","TestName");
        Long saveId = categoryService.saveCategory(categoryInfo);

        //when
        categoryService.deleteCategoryOld(saveId);

        //then
        IllegalArgumentException e =
                assertThrows(IllegalArgumentException.class,
                        () -> categoryService.findCategoryOld(saveId));
        assertThat(e.getMessage()).isEqualTo("찾는 카테고리 없습니다.");

    }

    @Test
    void 카테고리_업데이트()throws Exception {
        //given
        CategoryInfo categoryInfo = createCategoryInfo("TestBranch","TestCode","TestName");
        Long saveId = categoryService.saveCategory(categoryInfo);

        Category category = findCategory(saveId);
        CategoryInfo targetCategory = new CategoryInfo(category);
        targetCategory.setName("UpdateCategory");

        //when
        Long updateId = categoryService.updateCategory("TestBranch","TestCode",targetCategory);
        Category updateCategory =  findCategory(saveId);

        //then
        assertThat(updateCategory.getName()).isEqualTo("UpdateCategory");

    }
}