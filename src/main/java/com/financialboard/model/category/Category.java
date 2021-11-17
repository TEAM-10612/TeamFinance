package com.financialboard.model.category;

import com.financialboard.model.board.Board;
import com.financialboard.model.post.Post;
import lombok.AllArgsConstructor;
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
    private Long id;

    private String name;

    private LocalDateTime createTime;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post>posts = new ArrayList<>();
}
