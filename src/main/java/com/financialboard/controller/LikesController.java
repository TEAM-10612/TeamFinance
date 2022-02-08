package com.financialboard.controller;

import com.financialboard.annotation.CurrentUser;
import com.financialboard.annotation.LoginCheck;
import com.financialboard.common.config.PrincipalDetails;
import com.financialboard.service.LikesService;
import com.financialboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;
    private final PostService postService;

    @PostMapping(value = "/post/{postId}/likes")
    public ResponseEntity<?> likes(@PathVariable long postId , @CurrentUser PrincipalDetails principalDetails) {
        likesService.likes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 성공", HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{postId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable long postId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.unLikes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 취소 성공", HttpStatus.OK);
    }

}
