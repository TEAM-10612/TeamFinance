package com.financialboard.dto;



import com.financialboard.dto.UserDto.UserInfo;
import com.financialboard.dto.UserDto.UserInfoDto;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.post.Post;
import com.financialboard.model.post.PostCategory;
import com.financialboard.model.post.PostStandard;
import com.financialboard.model.user.User;
import lombok.*;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;



@Getter
@Builder
public class PostDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SaveRequest{

        private UserInfo author;

        @NotBlank
        private String title;

        @NotBlank
        @Length(min = 10, max = 300000000)
        private String content;

        private String postImgUrl;

        private PostCategory postCategory;

        public void setPostImgUrl(String postImgUrl) {
            this.postImgUrl = postImgUrl;
        }

        public Post toEntity(){
            return Post.builder()
                    .author(this.author.toEntity())
                    .title(this.title)
                    .content(this.content)
                    .postCategory(this.postCategory)
                    .postImgUrl(this.postImgUrl)
                    .build();
        }

        public void deleteImagePath() {
            setPostImgUrl(null);
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class PostInfoResponse{
        private Long id;
        private UserInfo author;
        private String title;
        private String content;
        private PostCategory postCategory;
        private String postImageUrl;

        public Post toEntity(){
            return Post.builder()
                    .author(this.author.toEntity())
                    .title(this.title)
                    .content(this.content)
                    .postCategory(this.postCategory)
                    .postImgUrl(this.postImageUrl)
                    .build();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class PostResponse {
        private Long id;
        private UserInfo author;
        private String title;
        private String content;
        private PostCategory postCategory;
        private List<Likes> likesList = new ArrayList<>();
        private List<Comment> comments = new ArrayList<>();

        @Builder
        public PostResponse(Long id, UserInfo author, String title, String content,
                            PostCategory postCategory, List<Likes> likesList, List<Comment> comments) {
            this.id = id;
            this.author = author;
            this.title = title;
            this.content = content;
            this.postCategory = postCategory;
            this.likesList = likesList;
            this.comments = comments;
        }
    }



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostInsertComment{
        private Long id;
        private UserDto.UserPostInfo author;
        private String title;
        private String content;
        private String postImageUrl;
        private PostCategory postCategory;

        public Post toEntity(){
            return Post.builder()
                    .author(this.author.toEntity())
                    .title(this.title)
                    .content(this.content)
                    .postImgUrl(this.postImageUrl)
                    .postCategory(this.postCategory)
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    public static class SearchPostResponse{
        private Long id;
        private UserDto.UserPostInfo author;
        private String title;
        private List<Comment>comments = new ArrayList<>();
        private List<Likes>likesList = new ArrayList<>();

        @Builder

        public SearchPostResponse(Long id, UserDto.UserPostInfo author, String title,
                                  List<Comment> comments, List<Likes> likesList) {
            this.id = id;
            this.author = author;
            this.title = title;
            this.comments = comments;
            this.likesList = likesList;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchCondition{
        private String keyword;
        private PostStandard orderStandard;
    }


}
