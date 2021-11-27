package com.financialboard.service;

import com.financialboard.exception.post.PostNotFoundException;
import com.financialboard.exception.user.UserNotFoundException;
import com.financialboard.model.likes.Likes;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.repository.LikesRepository;
import com.financialboard.repository.PostRepository;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likes(long postId,long userId){
        likesRepository.likes(postId,userId);
    }

    @Transactional
    public void unLikes(long postId,long userId){
        likesRepository.unlikes(postId, userId);
    }
}
