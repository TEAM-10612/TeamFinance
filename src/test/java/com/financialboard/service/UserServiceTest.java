package com.financialboard.service;

import com.financialboard.dto.UserDto;
import com.financialboard.encryption.EncryptionService;
import com.financialboard.exception.user.EmailDuplicateException;
import com.financialboard.exception.user.NicknameDuplicateException;
import com.financialboard.exception.user.UnauthenticatedUserException;
import com.financialboard.model.user.User;
import com.financialboard.repository.CategoryRepository;
import com.financialboard.repository.LikesRepository;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.comment.CommentRepository;
import com.financialboard.repository.post.PostRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    EncryptionService encryptionService;

    @Mock
    PostRepository postRepository;

    @Mock
    LikesRepository likesRepository;

    @Mock
    CommentRepository commentRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void saveUser(){
        userService.saveUser(createUser());
        userService.saveUser(createUser2());
    }

    public User userEntity(){
        return createUser().toEntity();
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



    private UserDto.SaveRequest createUser2(){
        UserDto.SaveRequest request = UserDto.SaveRequest.builder()
                .email("rdd2d@naver.com")
                .password("112111111")
                .phone("01035556666")
                .nickname("KyK2222")
                .build();

        return request;
    }


    @Test
    void 회원가입_O()throws Exception{
        //given
        UserDto.SaveRequest request = createUser();
        //when
        when(userRepository.existsByNickname("KKK")).thenReturn(false);
        when(userRepository.existsByEmail("rddd@naver.com")).thenReturn(false);
        userService.saveUser(request);
        verify(userRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("이메일 중복으로 회원가입 실패")
    void duplicateEmail()throws Exception{
        //given
        UserDto.SaveRequest user = createUser();
        //when
        when(userRepository.existsByNickname("rddd@naver.com")).thenReturn(true);

        //then
        Assertions.assertThrows(EmailDuplicateException.class,()-> userService.saveUser(user));
        verify(userRepository,atLeastOnce()).existsByEmail("rddd@naver.com");;
    }

    @Test
    @DisplayName("닉네임 중복으로 회원가입 실패")
    void duplicateNickname()throws Exception{
        //given
        UserDto.SaveRequest user = createUser();


        //when
        when(userRepository.existsByNickname("KKK1111")).thenReturn(true);

        //then
        Assertions.assertThrows(NicknameDuplicateException.class,()-> userService.saveUser(user));
        verify(userRepository,atLeastOnce()).existsByNickname("KKK1111");
    }

    @Test
    @DisplayName("비밀번호 찾기 성공-  전달 받은 이메일이 회원이라면 비밀번호 변경에 성공한다.")
    void updateForgotPassword()throws Exception{
        //given
        UserDto.ChangePasswordRequest  request =  UserDto.ChangePasswordRequest.builder()
                .email("kkk12@naver.com")
                .passwordAfter("11223334")
                .build();

        User user = createUser().toEntity();

        //when
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

        userService.updatePasswordByForgot(request);

        //then
        org.assertj.core.api.Assertions.assertThat(user.getPassword()).isEqualTo(request.getPasswordAfter());
        verify(userRepository,atLeastOnce()).findByEmail(request.getEmail());

    }

    @Test
    @DisplayName("비밀번호 변경 - 이전 비밀번호와 일치하면 비밀번호 변경에 성공힌다.")
    void updatePassword()throws Exception{
        //given
        User user = createUser().toEntity();
        UserDto.ChangePasswordRequest request = UserDto.ChangePasswordRequest.builder()
                .email(null)
                .passwordBefore("11111111")
                .passwordAfter("12121212")
                .build();
        String email = "rddd@naver.com";
        String passwordAfter = encryptionService.encrypt(request.getPasswordAfter());
        String passwordBefore = encryptionService.encrypt(request.getPasswordBefore());

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.existsByEmailAndPassword(email,passwordBefore)).thenReturn(true);
        userService.updatePassword(email,request);

        //then
        org.assertj.core.api.Assertions.assertThat(user.getPassword()).isEqualTo(passwordAfter);

        verify(userRepository, atLeastOnce()).findByEmail(email);
        verify(userRepository, atLeastOnce()).existsByEmailAndPassword(email,passwordBefore);
    }

    @Test
    @DisplayName("이전 비밀번호와 일치하지 않아 비밀번호 변경 실패")
    void failToUpdatePassword()throws Exception{
        //given
        User user = createUser().toEntity();
        UserDto.ChangePasswordRequest request = UserDto.ChangePasswordRequest.builder()
                .email(null)
                .passwordBefore("11111111")
                .passwordAfter("121313131")
                .build();

        String email = "rddd@naver.com";
        String passwordBefore = encryptionService.encrypt(request.getPasswordBefore());

        //when
        when(userRepository.existsByEmailAndPassword(email,passwordBefore)).thenReturn(false);

        //then
        Assertions.assertThrows(UnauthenticatedUserException.class,
                ()-> userService.updatePassword(email,request));

        verify(userRepository, atLeastOnce()).existsByEmailAndPassword(email,passwordBefore);

    }
}