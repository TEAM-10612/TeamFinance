package com.financialboard.service;

import com.financialboard.dto.UserDto;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.repository.post.PostRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;


    private UserDto.SaveRequest createUser(){
        UserDto.SaveRequest request = UserDto.SaveRequest.builder()
                .email("rddd@naver.com")
                .password("11111111")
                .phone("01055556666")
                .nickname("KKK1111")
                .build();

        return request;
    }



}