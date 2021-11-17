package com.financialboard.model.post;

import com.financialboard.model.category.Category;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id@GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categroy_id")
    private Category category;

    @OneToMany(mappedBy = "post" , fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}
