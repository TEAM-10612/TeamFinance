package com.financialboard.service;

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
        likesRepository.likes(postId,userId);
    }

    @Transactional
    public void unLikes(long postId,long userId){
        likesRepository.unlikes(postId, userId);
    }
}
