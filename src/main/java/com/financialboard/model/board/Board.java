package com.financialboard.model.board;

import com.financialboard.model.category.Category;
import com.financialboard.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


}
