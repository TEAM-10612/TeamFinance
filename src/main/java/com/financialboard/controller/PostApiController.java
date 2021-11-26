package com.financialboard.controller;

import com.financialboard.dto.PostDto;
import com.financialboard.exception.user.PostNotFoundException;
import com.financialboard.model.post.Post;
import com.financialboard.repository.PostRepository;
import com.financialboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.Optional;

import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<PostDto.SaveRequest> savePost(
            @Valid@RequestBody PostDto.SaveRequest request,
            @Valid@RequestBody MultipartFile multipartFile){

        postService.savePost(request,multipartFile);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto.PostInfoResponse> getPost(@PathVariable Long id){
        PostDto.PostInfoResponse postInfo = postService.getPostInfo(id);

        return ResponseEntity.ok(postInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostDto.SaveRequest> updatePost(@PathVariable Long id,
                                                          @Valid@RequestBody PostDto.SaveRequest request
            ,@Valid @Nullable @RequestBody MultipartFile multipartFile){
        updatePost(id,request,multipartFile);

        return ResponseEntity.ok(request);
    }
}
