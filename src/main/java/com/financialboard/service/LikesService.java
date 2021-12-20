package com.financialboard.service;

import com.financialboard.exception.ExistLikesException;
import com.financialboard.exception.LikesNotFoundException;
import com.financialboard.exception.user.UserNotFoundException;
import com.financialboard.model.comment.Comment;
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
    public void likes(long postId, long sessionId) {
        try {
            likesRepository.likes(postId, sessionId);
        } catch (Exception e) {
            throw new ExistLikesException("이미 좋아요 하였습니다.");
        }
    }

    @Transactional
    public void unLikes(long postId, long sessionId) {
        likesRepository.unLikes(postId, sessionId);
    }
}
