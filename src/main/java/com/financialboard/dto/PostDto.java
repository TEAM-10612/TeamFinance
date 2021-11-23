package com.financialboard.dto;


import com.financialboard.dto.CategoryDto.CategoryInfo;
import com.financialboard.dto.UserDto.UserInfo;
import com.financialboard.dto.UserDto.UserInfoDto;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

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
        private String content;
        private CategoryInfo category;
        private String postImageUrl;
    }

}
