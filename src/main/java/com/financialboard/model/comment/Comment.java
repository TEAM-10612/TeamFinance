package com.financialboard.model.comment;

import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;

import javax.persistence.*;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id@GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String comment;

    private LocalDateTime createTime;

    private LocalDateTime modifiedTime;

}
