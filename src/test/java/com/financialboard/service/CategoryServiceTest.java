package com.financialboard.service;

import com.financialboard.repository.UserRepository;
import com.financialboard.repository.CategoryRepository;
import com.financialboard.repository.category.CustomCategoryRepository;
import com.financialboard.repository.post.PostRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CustomCategoryRepository customCategoryRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    PostRepository postRepository;




//    Category category2 = new Category("sub1",0L);
//    public List<Category> createCategory(){
//        categoryService.createCategory();
//        Category category1 = new Category("sub1",0L);
//        Category category2 = new Category("sub1",0L);
//        Category category3 = new Category("sub1-1",1L);
//        Category category4 = new Category("sub1-2",1L);
//        Category category5 = new Category("sub2-1",2L);
//        Category category6 = new Category("sub2-2",2L);
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//        categoryRepository.save(category4);
//        categoryRepository.save(category5);
//        categoryRepository.save(category6);
//        ReflectionTestUtils.setField(category1,"id",1L);
//        ReflectionTestUtils.setField(category2,"id",2L);
//        ReflectionTestUtils.setField(category3,"id",3L);
//        ReflectionTestUtils.setField(category4,"id",4L);
//        ReflectionTestUtils.setField(category5,"id",5L);
//        ReflectionTestUtils.setField(category6,"id",6L);
//        User user = User.builder()
//                .id(1L)
//                .nickname("KK")
//                .email("rddd@naver.com")
//                .phone("01099992222")
//                .password("12345678")
//                .userGrade(UserGrade.BRONZE)
//                .userLevel(UserLevel.USER)
//                .build();
//        userRepository.save(user);
//
//        Post post = Post.builder()
//                .author(user)
//                .title("ppppppppp")
//                .content("집 좀 가자")
//                .postImgUrl(null)
//                .category(category2)
//                .build();
//        postRepository.save(post);
//
//        List<Category> categories = List.of(
//                category1,category2,category3,category4,category5,category6
//        );
//        return categories;
//    }
//
//
//
//    @Test
//    @DisplayName("최상위 카테고리 생성")
//    void parent_Category()throws Exception{
//        //given
//        List<Category> categories = createCategory();
//        given(categoryRepository.findAll())
//                .willReturn(categories);
//
//        //when
//        CategoryDto.CategoryInfo categoryInfo = categoryService.createCategory();
//
//        //then
//        verify(categoryRepository, atLeastOnce()).findAll();
//        assertThat(categoryInfo.getSubCategory()).size().isEqualTo(2);
//        assertThat(categoryInfo.getSubCategory().get(0).getSubCategory().size()).isEqualTo(2);
//        assertThat(categoryInfo.getSubCategory().get(1).getSubCategory().size()).isEqualTo(2);
//
//    }
//
//    @Test
//    @DisplayName("카테고리별 게시글 조회")
//    void getCategoryInPost()throws Exception{
//        //given
//        List<Category> category = createCategory();
//
//        //when
//
//        //then
//        System.out.println(categoryRepository.findAll());
//
//
//    }
}