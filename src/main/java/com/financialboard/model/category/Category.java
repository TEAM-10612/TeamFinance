package com.financialboard.model.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financialboard.dto.CategoryDto;
import com.financialboard.dto.CategoryDto.SaveRequest;
import com.financialboard.model.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category>childCategory =new ArrayList<>();

    @OneToMany(mappedBy ="category")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Category(String categoryName, Category parentCategory, List<Category> childCategory, List<Post> posts) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.posts = posts;
    }

    public CategoryDto.CategoryResponse toCategoryResponse(){
        return CategoryDto.CategoryResponse.builder()
                .id(this.id)
                .categoryName(this.categoryName)
                .subCategory(
                        this.childCategory.stream().collect(Collectors.toMap(
                                Category::getCategoryName, CategoryDto.CategoryInfo::new
                        ))
                )
                .build();
    }

    public void update(SaveRequest request){
        this.categoryName = request.getCategoryName();
        this.parentCategory = request.toEntity().getParentCategory();
    }
}
