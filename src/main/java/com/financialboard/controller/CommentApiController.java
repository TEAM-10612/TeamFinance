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


    @DeleteMapping("/{id}")
    @LoginCheck
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
