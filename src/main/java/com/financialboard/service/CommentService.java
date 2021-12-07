package com.financialboard.service;


import com.financialboard.dto.CommentDto.AddCommentRequest;
import com.financialboard.exception.post.comment.CommentNotFoundException;
import com.financialboard.exception.post.PostNotFoundException;
import com.financialboard.exception.user.UserNotFoundException;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.repository.post.PostRepository;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void saveComment(long postId, long userId, AddCommentRequest request){
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userRepository.findById(userId);

        if(post.isEmpty()){
            throw new PostNotFoundException();
        }else if (user.isEmpty()){
            throw new UserNotFoundException("존재하지 않는 회원입니다.");
        }
        commentRepository.save(request.toEntity());
    }

    @Transactional
    public void deleteComment(long Id){
        Optional<Comment> comment = commentRepository.findById(Id);
        if(comment == null){
            throw new CommentNotFoundException();
        }

        commentRepository.deleteById(Id);
    }


}
