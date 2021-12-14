package com.financialboard.dto;


import com.financialboard.dto.CategoryDto.CategoryInfo;
import com.financialboard.dto.UserDto.UserInfo;
import com.financialboard.dto.UserDto.UserInfoDto;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.post.Post;
import com.financialboard.model.post.PostStandard;
import com.financialboard.model.user.User;
import lombok.*;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static com.financialboard.dto.CategoryDto.*;

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

        @NotBlank
        private CategoryInfo categoryInfo;

        public void setPostImgUrl(String postImgUrl) {
            this.postImgUrl = postImgUrl;
        }

        public Post toEntity(){
            return Post.builder()
                    .author(this.author.toEntity())
                    .title(this.title)
                    .content(this.content)
                    .postImgUrl(this.postImgUrl)
                    .category(this.categoryInfo.toEntity())
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
        private UserInfoDto author;
        private String title;
        private String content;
        private CategoryInfo category;
        private String postImageUrl;
    }

    @NoArgsConstructor
    @Getter
    public static class PostResponse {
        private Long id;
        private UserInfoDto author;
        private String title;
        private String content;
        private CategoryInfo category;
        private List<Likes> likesList = new ArrayList<>();
        private List<Comment> comments = new ArrayList<>();

        @Builder
        public PostResponse(Long id, UserInfoDto author, String title, String content,
                            CategoryInfo category, List<Likes> likesList, List<Comment> comments) {
            this.id = id;
            this.author = author;
            this.title = title;
            this.content = content;
            this.category = category;
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
        private CategoryInfo category;
        private String postImageUrl;

        public Post toEntity(){
            return Post.builder()
                    .author(this.author.toEntity())
                    .title(this.title)
                    .content(this.content)
                    .postImgUrl(this.postImageUrl)
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    public static class SearchPostResponse{
        private Long id;
        private UserDto.UserPostInfo author;
        private String title;
        private CategoryByPostResponse category;
        private List<Comment>comments = new ArrayList<>();
        private List<Likes>likesList = new ArrayList<>();

        @Builder

        public SearchPostResponse(Long id, UserDto.UserPostInfo author, String title,
                                  CategoryByPostResponse category, List<Comment> comments, List<Likes> likesList) {
            this.id = id;
            this.author = author;
            this.title = title;
            this.category = category;
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchConditionByCategory{
        private Long categoryId;
    }

}
