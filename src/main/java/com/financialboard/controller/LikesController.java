package com.financialboard.controller;

import com.financialboard.annotation.CurrentUser;
import com.financialboard.annotation.LoginCheck;
import com.financialboard.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.Current;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/{postId}/likes")
    @LoginCheck
    public void likes(@PathVariable long postId, @CurrentUser long id){
        likesService.likes(postId,id);
    }

    @DeleteMapping("/{postId}/unlikes")
    @LoginCheck
    public void unlikes(@PathVariable long postId,@CurrentUser long id){
        likesService.unLikes(postId,id);
    }
}
