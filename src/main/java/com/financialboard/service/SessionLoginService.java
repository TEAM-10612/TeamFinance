package com.financialboard.service;

import com.financialboard.dto.UserDto;
import com.financialboard.encryption.EncryptionService;
import com.financialboard.exception.UnauthenticatedUserException;
import com.financialboard.exception.UserNotFoundException;
import com.financialboard.model.user.User;
import com.financialboard.model.user.UserLevel;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

import static com.financialboard.util.UserConstant.USER_ID;
import static com.financialboard.util.UserConstant.USER_STATE;

@Service
@RequiredArgsConstructor
public class SessionLoginService {

    private final HttpSession session;
    private final EncryptionService encryptionService;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void existByEmailAndPassword(UserDto.LoginRequest request) {
        request.passwordEncryption(encryptionService);
        String email = request.getEmail();
        String password = request.getPassword();
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new UserNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
    @Transactional(readOnly = true)
    public void login(UserDto.LoginRequest request) {
        existByEmailAndPassword(request);
        String email = request.getEmail();
        session.setAttribute(USER_ID,email);

    }

    public void logout() {
        session.removeAttribute(USER_ID);
    }

    public String getLoginUser(){
        return (String) session.getAttribute(USER_ID);
    }

    public UserDto.UserInfoDto getCurrentUser(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthenticatedUserException("존재하지 않는 사용자 입니다.")).toUserInfoDto();
    }

    public UserLevel getUserLevel() {
        return (UserLevel) session.getAttribute(USER_STATE);
    }


}
