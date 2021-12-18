package com.financialboard.service;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.UserDto;
import com.financialboard.model.post.Post;
import com.financialboard.model.post.PostCategory;
import com.financialboard.model.user.User;
import com.financialboard.model.user.UserGrade;
import com.financialboard.model.user.UserLevel;
import com.financialboard.repository.LikesRepository;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LikesServiceTest {

    @InjectMocks
    LikesService likesService;

    @Mock
    LikesRepository likesRepository;

    @Mock
    PostRepository postRepository;


    @Mock
    UserRepository userRepository;

    public UserDto.UserInfo info() {
        UserDto.UserInfo userInfo = UserDto.UserInfo.builder()
                .id(1L)
                .email("rdd@naver.com")
                .build();
        return userInfo;
    }

    public PostDto.SaveRequest postRequest() {
        PostDto.SaveRequest postRequest = PostDto.SaveRequest.builder()
                .author(info())
                .title("zzzzz")
                .content("첫게시글")
                .postImgUrl(null)

                .build();

        return postRequest;
    }

    public PostDto.PostInfoResponse postInfoResponse(){
        PostDto.PostInfoResponse response = PostDto.PostInfoResponse.builder()
                .id(1L)
                .author(info())
                .title("asdasdasdasd")
                .content("dasasddasdasdasdas")
                .postImageUrl(null)
                .postCategory(PostCategory.COIN)
                .build();
        return response;
    }

    @Test
    @DisplayName("insert Like")
    void like_O()throws Exception{
        //given
        Post post = postRepository.findById(1L).orElseThrow();
        User user = userRepository.findById(1L).orElseThrow();
        //when
        likesService.likes(post.getId(),user.getId());

        //then
        Assertions.assertThat(post.getLikesList().size()).isEqualTo(1);

    }

    @Test
    @DisplayName("unLike")
    void unLike()throws Exception{
        //given

        //when

        //then


    }
}