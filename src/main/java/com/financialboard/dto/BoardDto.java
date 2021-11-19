package com.financialboard.dto;

import com.financialboard.model.board.Board;
import lombok.*;

@Getter
@Builder
public class BoardDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class BoardInfo{
        private Long id;
        private String name;

        public Board toEntity(){
            return Board.builder()
                    .id(this.id)
                    .name(this.name)
                    .build();
        }
    }
}
