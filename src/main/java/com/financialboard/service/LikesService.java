package com.financialboard.service;

import com.financialboard.exception.PostNotFoundException;
import com.financialboard.exception.UserNotFoundException;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.repository.LikesRepository;
import com.financialboard.repository.PostRepository;
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
    public boolean addLike(Long userId,Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->new UserNotFoundException("존재하지 않는 회원"));

        if(isNOtAlreadyLike(user,post)){
            likesRepository.save(new Likes(post,user));
            return true;
        }

        return false;
    }

    private boolean isNOtAlreadyLike(User user, Post post) {
        return likesRepository.findByUserAndPost(user, post).isEmpty();
    }

}
