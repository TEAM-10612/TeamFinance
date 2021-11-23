package com.financialboard.model.category;

import com.financialboard.dto.CategoryDto;
import com.financialboard.model.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String branch;

    private String code;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory",cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    private Integer level;

    @Builder
    public Category( String branch, String code, String name,
                    Category parentCategory,Integer level) {
        this.branch = branch;
        this.code = code;
        this.name = name;
        this.parentCategory = parentCategory;
        this.level = level;

    }


}
