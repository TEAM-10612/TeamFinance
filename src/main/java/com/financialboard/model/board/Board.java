package com.financialboard.model.board;

import com.financialboard.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board{

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User author;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime modifiedTime;
}
