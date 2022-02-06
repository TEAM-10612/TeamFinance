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

        private String content;


        public Comment toEntity(){
            return Comment.builder()
                    .content(this.content)
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    public static class ListComments{

        private Long id;
        private String content;
        private UserInfo author;
        private PostInsertComment post;

        public ListComments(Long id, String content, UserInfo author, PostInsertComment post) {
            this.id = id;
            this.content = content;
            this.author = author;
            this.post = post;
        }
    }
}
