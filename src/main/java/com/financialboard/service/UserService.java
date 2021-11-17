package com.financialboard.service;

import com.financialboard.dto.UserDto;
import com.financialboard.exception.EmailDuplicateException;
import com.financialboard.exception.NicknameDuplicateException;
import com.financialboard.model.user.User;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

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

    @Transactional(readOnly = true)
    public boolean nicknameDuplicateCheck(String nickname) {
        return userRepository.existByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean emailDuplicateCheck(String email) {
        return userRepository.existByEmail(email);
    }
}
