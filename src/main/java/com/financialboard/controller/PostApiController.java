package com.financialboard.controller;

import com.financialboard.annotation.CurrentUser;
import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.PostDto;
import com.financialboard.exception.user.UserMissMatchException;
import com.financialboard.model.post.Post;
import com.financialboard.repository.post.PostRepository;
import com.financialboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping
    @LoginCheck
    public ResponseEntity<PostDto.SaveRequest> savePost(
            @Valid@RequestBody PostDto.SaveRequest request,
            @Valid@RequestBody MultipartFile multipartFile){

        postService.savePost(request,multipartFile);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/{id}")
    @LoginCheck
    public ResponseEntity<PostDto.PostInfoResponse> getPost(@PathVariable long id){
        PostDto.PostInfoResponse postInfo = postService.getPostInfo(id);

        return ResponseEntity.ok(postInfo);
    }

    @DeleteMapping("/{id}")
    @LoginCheck
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId , @CurrentUser Long id){
        Post post = postRepository.findByPostId(postId);
        if(post.getAuthor().getId() != id){
            throw new UserMissMatchException();
        }
        postService.deletePost(postId,id);
    }

    @PatchMapping("/{id}")
    @LoginCheck
    public ResponseEntity<PostDto.SaveRequest> updatePost(@PathVariable Long id,
                                                          @Valid@RequestBody PostDto.SaveRequest request
            ,@Valid @Nullable @RequestBody MultipartFile multipartFile){
        updatePost(id,request,multipartFile);

        return ResponseEntity.ok(request);
    }


}
