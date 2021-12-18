package com.financialboard.model.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financialboard.dto.PostDto;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.user.BaseTimeEntity;
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
public class Post extends BaseTimeEntity {

    @Id@GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    private String title;

    private String content;

    private String postImgUrl;

    @Enumerated(EnumType.STRING)
    private PostCategory postCategory;

    @OneToMany(mappedBy = "post" , fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"post"})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"post"})
    private List<Likes> likesList = new ArrayList<>();

    @Builder
    public Post(User author, String title, String content, String postImgUrl,PostCategory postCategory) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.postImgUrl = postImgUrl;
        this.postCategory = postCategory;
    }

    public void update(PostDto.SaveRequest request){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.postCategory = request.getPostCategory();
        this.postImgUrl = request.getPostImgUrl();
    }

    public PostDto.PostInfoResponse toPostInfo(){
        return PostDto.PostInfoResponse.builder()
                .id(this.id)
                .author(this.author.toUserInfo())
                .content(this.content)
                .postCategory(this.postCategory)
                .postImageUrl(this.postImgUrl)
                .build();
    }
}
