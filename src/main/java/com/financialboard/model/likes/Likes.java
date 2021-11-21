package com.financialboard.model.likes;

import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @Builder
    public Likes(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
