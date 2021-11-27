package com.financialboard.dto;

import com.financialboard.dto.PostDto.PostInsertComment;
import com.financialboard.dto.UserDto.UserInfo;
import com.financialboard.model.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddCommentRequest{

        private String comment;
        private UserInfo author;
        private PostInsertComment post;


        public Comment toEntity(){
            return Comment.builder()
                    .comment(this.comment)
                    .author(this.author.toEntity())
                    .post(this.post.toEntity())
                    .build();
        }
    }
}
