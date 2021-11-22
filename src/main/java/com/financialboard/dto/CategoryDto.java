package com.financialboard.dto;

import com.financialboard.model.category.Category;
import lombok.*;

import java.util.Map;
import java.util.stream.Collectors;


@Getter
@Builder
public class CategoryDto {

    @Getter
    @NoArgsConstructor
    @Builder
    public static class CategoryInfo{
        private Long id;
        private String branch;
        private String code;
        private String parentCategoryName;
        private Integer level;
        private String name;
        private Map<String,CategoryInfo> childCategory;

        public CategoryInfo(Category entity){
            this.id = entity.getId();
            this.branch = entity.getBranch();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.level = entity.getLevel();
            if (entity.getParentCategory() == null) {
                this.parentCategoryName = "대분류";
            }else {
                this.parentCategoryName =entity.getParentCategory().getName();
            }

            this.childCategory = entity.getSubCategory() == null ? null :
                    entity.getSubCategory().stream().collect(Collectors.toMap(
                            Category::getCode, CategoryInfo::new
                    ));
        }

        public Category toEntity(){
            return Category.builder()
                    .branch(branch)
                    .code(code)
                    .level(level)
                    .name(name)
                    .build();
        }
    }


    @Getter
    @Setter
    @Builder
    public static class CategoryReturnDto {

        private Long max_level;

        private Map<String, CategoryInfo> categories;

    }
}
