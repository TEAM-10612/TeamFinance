package com.financialboard.service;


import com.financialboard.model.category.Category;
import com.financialboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.financialboard.dto.CategoryDto.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Transactional
    public void createCategory(SaveRequest request){
        Boolean existByName = categoryRepository.existsByCategoryName(request.getCategoryName());
        if (existByName){
            throw new IllegalArgumentException("카테고리 중복");
        }

        if (request.getParentCategory() == null){
            SaveRequest rootCategory = SaveRequest.builder()
                    .categoryName(request.getCategoryName())
                    .parentCategory(null)
                    .build();

            categoryRepository.save(rootCategory.toEntity());
        }else{
            String parentCategory1 = request.getParentCategory();
            Category parentCategory = categoryRepository.findByCategoryName(parentCategory1)
                    .orElseThrow();
            Category category = Category.builder()
                    .categoryName(request.getCategoryName())
                    .parentCategory(parentCategory)
                    .build();
            parentCategory.getChildCategory().add(request.toEntity());
            categoryRepository.save(category);
        }


    }
}
