package com.financialboard.controller;

import com.financialboard.dto.PostDto;
import com.financialboard.repository.PostRepository;
import com.financialboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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

    @GetMapping
    public ResponseEntity<PostDto.PostInfoResponse> getPost(@RequestBody Long id){
        PostDto.PostInfoResponse postInfo = postService.getPostInfo(id);

        return ResponseEntity.ok(postInfo);
    }
}
