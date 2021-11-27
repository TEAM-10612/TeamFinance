package com.financialboard.model.comment;

import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
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

    @PrePersist
    public void createTime(){
        this.createTime = LocalDateTime.now();
    }

    @Builder
    public Comment(User author, Post post, String comment) {
        this.author = author;
        this.post = post;
        this.comment = comment;
    }
}
