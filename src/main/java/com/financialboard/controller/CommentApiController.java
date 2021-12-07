package com.financialboard.controller;

import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.CommentDto.AddCommentRequest;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentApiController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping
    @LoginCheck
    public void saveComment(@RequestBody AddCommentRequest request){
        Long post = request.getPost().getId();
        Long user = request.getAuthor().getId();
        commentService.saveComment(post,user,request);
    }

    @DeleteMapping("/{id}")
    @LoginCheck
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
