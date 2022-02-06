package com.financialboard.service;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.UserDto;
import com.financialboard.exception.user.UserNotFoundException;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.post.Post;
import com.financialboard.model.post.PostCategory;
import com.financialboard.model.user.User;
import com.financialboard.model.user.UserGrade;
import com.financialboard.model.user.UserLevel;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.repository.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static com.financialboard.dto.CommentDto.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

}