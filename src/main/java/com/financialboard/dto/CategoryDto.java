package com.financialboard.dto;

import com.financialboard.model.category.Category;
import com.financialboard.model.post.Post;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class CategoryDto {

    @Getter
    @NoArgsConstructor
    public static class SaveRequest{
        private String categoryName;
        private String parentCategory;
        private Map<String,CategoryInfo> subCategory;

        @Builder
        public SaveRequest(String categoryName, String parentCategory, Map<String, CategoryInfo> subCategory) {
            this.categoryName = categoryName;
            this.parentCategory = parentCategory;
            this.subCategory = subCategory;
        }



        public Category toEntity(){
            Category category = Category.builder()
                    .categoryName(categoryName)
                    .build();
            return category;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class CategoryInfo{
        private Long id;
        private String categoryName;
        private String parentCategory;

        @Builder
        public CategoryInfo(Category category) {
            this.id = category.getId();
            this.categoryName = category.getCategoryName();
            this.parentCategory = category.getParentCategory().getCategoryName();
        }

        public Category toEntity(){
            Category category = Category.builder()
                    .categoryName(categoryName)
                    .build();

            return category;
        }

    }

    @Getter
    @NoArgsConstructor
    @Builder
    public static class CategoryResponse{
        private Long id;
        private String categoryName;
        private Map<String,CategoryInfo> subCategory=new HashMap<>();

        public CategoryResponse(Long id, String categoryName, Map<String, CategoryInfo> subCategory) {
            this.id = id;
            this.categoryName = categoryName;
            this.subCategory = subCategory;
        }
    }

    @Getter
    @NoArgsConstructor
    @Builder
    public static class CategoryByPostResponse{
        private Long id;
        private String categoryName;

        public CategoryByPostResponse(Long id, String categoryName) {
            this.id = id;
            this.categoryName = categoryName;
        }
    }
}
