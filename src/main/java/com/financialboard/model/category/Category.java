package com.financialboard.model.category;

import com.financialboard.dto.CategoryDto;
import com.financialboard.model.board.Board;
import com.financialboard.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id@GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Post>posts = new ArrayList<>();
    @Builder
    public Category(Long id, String name, LocalDateTime createTime, Board board) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.board = board;
    }

    public CategoryDto.CategoryInfo categoryInfo(){
        return CategoryDto.CategoryInfo.builder()
                .name(this.name)
                .boardInfo(this.board.boardInfo())
                .build();
    }
}
