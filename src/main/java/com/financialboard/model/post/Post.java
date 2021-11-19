package com.financialboard.model.post;

import com.financialboard.dto.PostDto;
import com.financialboard.model.category.Category;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    private String postImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categroy_id")
    private Category category;

    @OneToMany(mappedBy = "post" , fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Likes> likesList = new ArrayList<>();

    @Builder
    public Post(User author, String title, String content, String postImgUrl, Category category) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.postImgUrl = postImgUrl;
        this.category = category;
    }

    public void update(PostDto.SaveRequest request){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.postImgUrl = request.getPostImgUrl();
        this.category = request.getCategoryInfo().toEntity();
    }

    public PostDto.PostInfoResponse toPostInfo(){
        return PostDto.PostInfoResponse.builder()
                .id(this.id)
                .author(this.author.toUserInfoDto())
                .content(this.content)
                .category(this.category.categoryInfo())
                .postImageUrl(this.postImgUrl)
                .build();
    }
}
