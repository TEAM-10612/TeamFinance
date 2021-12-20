package com.financialboard.service;

import com.financialboard.dto.CommentDto;
import com.financialboard.dto.PostDto;
import com.financialboard.dto.PostDto.PostInsertComment;
import com.financialboard.dto.UserDto;
import com.financialboard.dto.UserDto.UserInfo;
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
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.financialboard.dto.CommentDto.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;


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

    public UserDto.UserPostInfo userInfo(){
        UserDto.UserPostInfo userInfo = UserDto.UserPostInfo.builder()
                .id(1L)
                .nickname("kqwewe")
                .userGrade(UserGrade.SILVER)
                .userLevel(UserLevel.USER)
                .build();
        return userInfo;
    }
    public UserDto.UserInfo userInfo1(){
        UserDto.UserInfo userInfo = UserDto.UserInfo.builder()
                .id(1L)
                .email("rdd@naver.com")
                .build();
        return userInfo;
    }
    public PostInsertComment comment (){
        PostInsertComment insertComment = PostInsertComment.builder()
                .id(1L)
                .author(userInfo())
                .content("whgdkdy xptxmmgksms rprud")
                .title("wpahrdlekdlede")
                .postImageUrl(null)
                .postCategory(PostCategory.STOCK)
                .build();

        return insertComment;
    }



    public AddCommentRequest request(){
        AddCommentRequest request = AddCommentRequest.builder()
                .author(userInfo1())
                .post(comment())
                .content("댓글등록 테스트")
                .build();

        return request();
    }
    private User user;
    private Post post;
    @BeforeEach
    public void setUp(){
      user = User.builder().id(1L).nickname("KKK")
                .email("rdj1014@naver.com").phone("01098213332").userLevel(UserLevel.USER)
                .userGrade(UserGrade.SILVER).password("asdsaddasw").build();
      post = Post.builder().id(1L).author(user).title("adsadsadsad").content("dasdasdasdasss")
                .postImgUrl(null).postCategory(PostCategory.STOCK).build();
    }


    @Test
    @DisplayName("댓글 등록")
    void insertComment()throws Exception{
        //given
        Comment comment = Comment.builder()
                .author(user)
                .post(post)
                .content("xxxxxxxxx")
                .build();

        //when
        given(postRepository.findById(anyLong())).willReturn(Optional.ofNullable(post));
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(user));
        given(commentRepository.save(any())).willReturn(comment);
        Comment saveComment = commentService.saveComment(post.getId(), user.getId(), "xxxxxxxxx");

        //then
        Assertions.assertThat(saveComment.getPost().getId()).isEqualTo(post.getId());
        Assertions.assertThat(saveComment.getAuthor().getId()).isEqualTo(user.getId());
        Assertions.assertThat(saveComment.getContent()).isEqualTo("xxxxxxxxx");
    }

    @Test
    public void addComment_실패() throws Exception {
        //given
        given(postRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(post));
        given(userRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(null));

        //when
        assertThrows(UserNotFoundException.class, () -> {
            Comment result = commentService.saveComment(post.getId(), user.getId(),"xxxxxxxxx");
        });
    }


}