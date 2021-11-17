package com.financialboard.model.comment;

import com.financialboard.model.post.Post;

import javax.persistence.*;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id@GeneratedValue
    private Long id;

    private String comment;

    private LocalDateTime createTime;

    private LocalDateTime modifiedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
}
