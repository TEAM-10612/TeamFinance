package com.financialboard.controller;

import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.CommentDto;
import com.financialboard.dto.CommentDto.AddCommentRequest;
import com.financialboard.repository.CommentRepository;
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
        Long postId = request.getPost().getId();
        Long userId = request.getAuthor().getId();
        commentService.saveComment(postId,userId,request);
    }

    @DeleteMapping("/{id}")
    @LoginCheck
    public void deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
    }
}
