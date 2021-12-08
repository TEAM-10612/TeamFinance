package com.financialboard.service;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.UserDto;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.UserGrade;
import com.financialboard.model.user.UserLevel;
import com.financialboard.repository.CategoryRepository;
import com.financialboard.repository.LikesRepository;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.repository.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PostRepository postRepository;

    @Mock
    LikesRepository likesRepository;

    @Mock
    CommentRepository commentRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    PostService postService;

    public UserDto.UserInfo userInfo(){
        UserDto.UserInfo userInfo = UserDto.UserInfo.builder()
                .id(1L)
                .email("rdd@naver.com")
                .nickname("KKKKKK")
                .userGrade(UserGrade.BRONZE)
                .userLevel(UserLevel.USER)
                .build();
        return userInfo;
    }

    public PostDto.SaveRequest createPost(){
        PostDto.SaveRequest request = PostDto.SaveRequest.builder()
                .author(userInfo())
                .title("zzzzz")
                .content("첫게시글")
                .postImgUrl(null)
                //.categoryInfo(categoryInfo())
                .build();

        return request;
    }
    public PostDto.SaveRequest createPost2(){
        PostDto.SaveRequest request = PostDto.SaveRequest.builder()
                .author(userInfo())
                .title("zqeqeqweqwzz")
                .content("2게시글")
                .postImgUrl(null)
                //.categoryInfo(categoryInfo())
                .build();

        return request;
    }
//    public CategoryDto.CategoryInfo categoryInfo (){
//        CategoryDto.CategoryInfo categoryInfo = CategoryDto.CategoryInfo.builder()
//                .id(1L)
//                .branch("branch1")
//                .code("K")
//                .parentCategoryName(null)
//                .level(0)
//                .name("주식")
//                .childCategory(null)
//                .build();
//        return categoryInfo;
//    }

    @Test
    @DisplayName("게시글 업로드 ")
    void savePost()throws Exception{
        //given
        PostDto.SaveRequest post = createPost();

        //when
        postService.savePost(post,null);

        //then
        verify(postRepository,atLeastOnce()).save(any());

    }

    @Test
    @DisplayName("게시글 삭제")
    void deletePost()throws Exception{
        //given
        PostDto.SaveRequest request = createPost();
        Post post = createPost().toEntity();
        //when
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        postService.deletePost(post.getId(), request.getAuthor().getId());

        //then
        verify(postRepository,atLeastOnce()).deleteById(any());
    }

    @Test
    @DisplayName("게시글 수정")
    void updatePost()throws Exception{
        //given
        PostDto.SaveRequest request = createPost();
        PostDto.SaveRequest request2 = createPost2();
        Post post = createPost().toEntity();
        Post post2 = createPost2().toEntity();

        //when
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        postService.updatePost(post.getId(),request2,null);


        //then
        Assertions.assertThat(post.getContent()).isEqualTo("2게시글");
    }

}
