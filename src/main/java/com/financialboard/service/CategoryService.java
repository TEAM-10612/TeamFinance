package com.financialboard.service;


import com.financialboard.dto.CategoryDto;
import com.financialboard.dto.CategoryDto.CategoryInfo;
import com.financialboard.exception.category.ExistsBranchAndNameException;
import com.financialboard.exception.category.NotFoundBranchException;
import com.financialboard.exception.category.NotFoundCategoryException;
import com.financialboard.exception.category.NotParentCategoryException;
import com.financialboard.model.category.Category;
import com.financialboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.financialboard.dto.CategoryDto.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveCategory(CategoryInfo categoryDto){
        Category category = categoryDto.toEntity();

        if(categoryDto.getParentCategoryName() == null) {
            if (categoryRepository.existsByBranchAndName(categoryDto.getBranch(), categoryDto.getName())) {
                throw new ExistsBranchAndNameException("브랜치와 이름이; 같을 수 없습니다.");
            }
            Category rootCategory = categoryRepository.findByBranchAndCode(categoryDto.getBranch(), "Root")
                    .orElseGet(
                            () ->
                                    Category.builder()
                                            .name("Root")
                                            .code("Root")
                                            .branch(categoryDto.getBranch())
                                            .level(0)
                                            .build()
                    );

            if (!categoryRepository.existsByBranchAndName(categoryDto.getBranch(), "Root")) {
                categoryRepository.save(rootCategory);
            }
            category.setParentCategory(rootCategory);
            category.setLevel(1);
        }else {
            String parentCategoryName = categoryDto.getParentCategoryName();
            Category parentCategory = categoryRepository.findByBranchAndCode(categoryDto.getBranch(),parentCategoryName)
                    .orElseThrow(
                            () -> new NotParentCategoryException("부모 카테고리 없음")
                    );
            category.setLevel(categoryDto.getLevel()+1);
            category.setParentCategory(parentCategory);
            parentCategory.getSubCategory().add(category);
        }
        return categoryRepository.save(category).getId();
    }

    @Transactional(readOnly = true)
    public CategoryReturnDto getCategoryBranch(String branch){
        Category category = findCategory(branch, "Root");
        CategoryInfo categoryInfo = new CategoryInfo(category);
        Long max_level = categoryRepository.maxLevel(branch);


        return CategoryReturnDto.builder()
                .max_level(max_level)
                .categories(categoryInfo.getChildCategory())
                .build();
    }

    @Transactional
    public int deleteCategory(String branch,String code){
        int value = 0;
        Category category = findCategory(branch, code);

        if (category.getSubCategory().size()==0){
            if(!category.getParentCategory().getCode().equals("Root")){
                Category parentCategory = findCategory(category.getParentCategory().getBranch(), category.getParentCategory().getCode());
                parentCategory.getSubCategory().remove(category);
            }
            value = categoryRepository.deleteByBranchAndCode(category.getBranch(), category.getCode());
        }else {
            category.setCode("Deleted Category :" + category.getCode());
            value = 2;

        }
        return value;
    }



    @Transactional
    public void deleteCategoryOld(Long categoryId){
        Category category = findCategoryOld(categoryId);

        if(category.getSubCategory().size() == 0){
            Category parentCategory = findCategoryOld(category.getParentCategory().getId());
            if(!parentCategory.getSubCategory().equals("Root")){
                parentCategory.getSubCategory().remove(category);
            }
            categoryRepository.deleteById(category.getId());
        }else{
            Category parentCategory = findCategoryOld(category.getParentCategory().getId());
            if(!parentCategory.getSubCategory().equals("Root")){
                parentCategory.getSubCategory().remove(category);
            }
            category.setName("Deleted category");
        }
    }
    @Transactional
    public Long updateCategory(String branch, String code,CategoryInfo categoryInfo){
        Category category = findCategory(branch, code);
        category.setName(categoryInfo.getName());

        return category.getId();
    }



    public List<Category> categories () {
        return categoryRepository.findAll();
    }

    //카테고리 하나 찾아오기 메소드
    public Category findCategoryOld (Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundCategoryException("찾는 카테고리 없습니다."));
    }

    private Category findCategory (String branch, String code) {
        return categoryRepository.findByBranchAndCode(branch, code)
                .orElseThrow(() -> new NotFoundCategoryException("카테고리를 찾을 수 없습니다. "));
    }
}
