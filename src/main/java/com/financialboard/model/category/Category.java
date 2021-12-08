package com.financialboard.model.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financialboard.dto.CategoryDto;
import com.financialboard.model.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
    private List<Category>childCategory =new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy ="category")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Category(String categoryName, Category parentCategory, List<Category> childCategory, List<Post> posts) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.posts = posts;
    }
}
