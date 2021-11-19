package com.financialboard.dto;

import com.financialboard.model.category.Category;
import lombok.*;

import static com.financialboard.dto.BoardDto.*;

@Getter
@Builder
public class CategoryDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CategoryInfo{
        private Long id;
        private String name;
        private BoardInfo boardInfo;

        public Category toEntity (){
            return Category.builder()
                    .id(this.id)
                    .name(this.name)
                    .board(this.boardInfo.toEntity())
                    .build();
        }
    }
}
