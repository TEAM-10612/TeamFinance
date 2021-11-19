package com.financialboard.service;

import com.financialboard.dto.UserDto;
import com.financialboard.encryption.EncryptionService;
import com.financialboard.exception.EmailDuplicateException;
import com.financialboard.exception.NicknameDuplicateException;
import com.financialboard.exception.UserNotFoundException;
import com.financialboard.exception.WrongPasswordException;
import com.financialboard.model.user.User;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;

    @Transactional
    public void saveUser(UserDto.SaveRequest request){
        if(emailDuplicateCheck(request.getEmail())){
            throw new EmailDuplicateException();
        }
        if(nicknameDuplicateCheck(request.getNickname())){
            throw new NicknameDuplicateException();
        }

        User user = userRepository.save(request.toEntity());

    }

    @Transactional
    public void deleteUser(String email,String password){

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 사용자입니다.")
        );

        if(!userRepository.existsByEmailAndPassword(email,encryptionService.encrypt(password))){
            throw new WrongPasswordException();
        }

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
}
