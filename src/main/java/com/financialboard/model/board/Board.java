package com.financialboard.model.board;

import com.financialboard.dto.BoardDto;
import com.financialboard.model.category.Category;
import com.financialboard.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board{

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "board",fetch = FetchType.LAZY)
    private List<Category>categoryList = new ArrayList<>();

    @Builder
    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BoardDto.BoardInfo boardInfo(){
        return BoardDto.BoardInfo.builder()
                .name(this.name)
                .build();
    }
}
