package com.financialboard.service;

import com.financialboard.dto.UserDto;
import com.financialboard.dto.UserDto.SaveRequest;
import com.financialboard.encryption.EncryptionService;
import com.financialboard.exception.user.*;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import com.financialboard.repository.UserRepository;
import com.financialboard.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final EncryptionService encryptionService;

    @Transactional
    public void saveUser(SaveRequest request){
        if(emailDuplicateCheck(request.getEmail())){
            throw new EmailDuplicateException();
        }
        if(nicknameDuplicateCheck(request.getNickname())){
            throw new NicknameDuplicateException();
        }
        request.passwordEncryption(encryptionService);
        userRepository.save(request.toEntity());
    }

    @Transactional
    public void deleteUser(String email,String password){

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 사용자입니다.")
        );

        if(!userRepository.existsByEmailAndPassword(email,encryptionService.encrypt(password))){
            throw new WrongPasswordException();
        }
        postRepository.delete((Post) user.getPosts());
        userRepository.deleteByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean nicknameDuplicateCheck(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean emailDuplicateCheck(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void updatePasswordByForgot(UserDto.ChangePasswordRequest request){
        String email = request.getEmail();
        request.passwordEncryption(encryptionService);

        User user = userRepository.findByEmail(email).orElseThrow(
                () ->  new UserNotFoundException("존재하지 않는 사용자 입니다.")
        );

        user.updatePassword(request.getPasswordAfter());
    }

    @Transactional
    public void updatePassword(String email, UserDto.ChangePasswordRequest request){
        request.passwordEncryption(encryptionService);
        String beforePassword =  request.getPasswordBefore();
        String afterPassword =  request.getPasswordAfter();

        if(!userRepository.existsByEmailAndPassword(email,beforePassword)){
            throw new UnauthenticatedUserException("이전 비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthenticatedUserException("Unauthenticated user")
        );

        user.updatePassword(afterPassword);
    }


}
