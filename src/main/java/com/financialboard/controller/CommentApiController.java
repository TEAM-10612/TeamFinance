package com.financialboard.controller;

import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.CommentDto.AddCommentRequest;
import com.financialboard.exception.user.UserNotFoundException;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.repository.post.PostRepository;
import com.financialboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentApiController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
    public Comment addComment (String text, long postId, long sessionId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(sessionId).orElseThrow(()->{
            return new UserNotFoundException("유저 아이디를 찾을 수 없습니다.");
        });
        Comment comment = Comment.builder()
                .content(text)
                .post(post)
                .author(user)
                .build();
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    @LoginCheck
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
