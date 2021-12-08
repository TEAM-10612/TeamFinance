package com.financialboard.dto;

import com.financialboard.model.category.Category;
import com.financialboard.model.post.Post;
import lombok.*;

import java.util.List;
import java.util.Map;


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
    @Setter
    public static class CategoryInfo{

        private String categoryName;
        private Category parentCategory;
        private List<Category> subCategory;
        private List<Post> posts;


        public CategoryInfo(String categoryName, Category parentCategory, List<Category> subCategory, List<Post> posts) {
            this.categoryName = categoryName;
            this.parentCategory = parentCategory;
            this.subCategory = subCategory;
            this.posts = posts;
        }

        public Category toEntity(){
            Category category = Category.builder()
                    .categoryName(categoryName)
                    .parentCategory(parentCategory)
                    .childCategory(subCategory)
                    .posts(posts)
                    .build();

            return category;
        }

    }
}
