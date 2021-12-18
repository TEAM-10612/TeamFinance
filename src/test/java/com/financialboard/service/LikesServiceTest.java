package com.financialboard.service;

import com.financialboard.dto.CategoryDto;
import com.financialboard.dto.PostDto;
import com.financialboard.dto.UserDto;
import com.financialboard.model.category.Category;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.model.user.UserGrade;
import com.financialboard.model.user.UserLevel;
import com.financialboard.repository.CategoryRepository;
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
    @InjectMocks
    CategoryService categoryService;
    @Mock
    LikesRepository likesRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UserRepository userRepository;

    public UserDto.UserInfo info() {
        UserDto.UserInfo userInfo = UserDto.UserInfo.builder()
                .id(1L)
                .email("rdd@naver.com")
                .nickname("KKKKKK")
                .userGrade(UserGrade.BRONZE)
                .userLevel(UserLevel.USER)
                .build();
        return userInfo;
    }
    private UserDto.SaveRequest createUser(){
        UserDto.SaveRequest request = UserDto.SaveRequest.builder()
                .email("rddd@naver.com")
                .password("11111111")
                .phone("01055556666")
                .nickname("KKK1111")
                .build();

        return request;
    }
    public CategoryDto.SaveRequest categoryRequest1 () {
        CategoryDto.SaveRequest saveRequest = CategoryDto.SaveRequest.builder()
                .categoryName("category")
                .parentCategory(null)
                .build();
        return saveRequest;
    }
    public CategoryDto.SaveRequest categoryRequest () {
        CategoryDto.SaveRequest saveRequest = CategoryDto.SaveRequest.builder()
                .categoryName("category1")
                .parentCategory("category")
                .build();
        return saveRequest;
    }

    public CategoryDto.CategoryInfo categoryInfo () {
        CategoryDto.CategoryInfo categoryInfo = CategoryDto.CategoryInfo.builder()
                .category(categoryRequest().toEntity())
                .build();

        return categoryInfo;
    }

    public PostDto.SaveRequest postRequest() {
        PostDto.SaveRequest postRequest = PostDto.SaveRequest.builder()
                .author(info())
                .title("zzzzz")
                .content("첫게시글")
                .postImgUrl(null)
                .categoryInfo(categoryInfo())
                .build();

        return postRequest;
    }

    @BeforeEach
    public void setUp(){
        categoryRepository.save(categoryInfo().toEntity());
        postRepository.save(postRequest().toEntity());
        userRepository.save(createUser().toEntity());
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