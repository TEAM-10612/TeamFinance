package com.financialboard.controller;

import com.financialboard.annotation.CurrentUser;
import com.financialboard.annotation.LoginCheck;
import com.financialboard.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/likes/{postId}")
    @LoginCheck
    public ResponseEntity likes(@PathVariable Long postId, @CurrentUser Long userId){
        likesService.likes(postId,userId);
        return OK;
    }

    @DeleteMapping("/unlikes/{postId}")
    @LoginCheck
    public void unlikes(@PathVariable Long postId,@CurrentUser Long id){
        likesService.unLikes(postId,id);
    }
}
