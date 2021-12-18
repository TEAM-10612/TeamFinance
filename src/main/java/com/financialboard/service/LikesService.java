package com.financialboard.service;

import com.financialboard.exception.ExistLikesException;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.repository.LikesRepository;
import com.financialboard.repository.post.PostRepository;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likes(long postId,long userId){
        User user = userRepository.findById(userId).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        if(isNotAlreadyLike(user,post)){
            likesRepository.save(Likes.builder().post(post).user(user).build());
        }else{
            throw new ExistLikesException("이미 좋아요를 누른 게시글입니다.");
        }
    }
    private boolean isNotAlreadyLike(User user, Post post) {
        return likesRepository.findByUserAndPost(user, post).isEmpty();
    }


}
