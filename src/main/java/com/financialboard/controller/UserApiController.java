package com.financialboard.controller;

import com.financialboard.annotation.CurrentUser;
import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.UserDto;
import com.financialboard.dto.UserDto.PasswordRequest;
import com.financialboard.dto.UserDto.SaveRequest;
import com.financialboard.service.SessionLoginService;
import com.financialboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import static com.financialboard.util.ResponseConstants.CREATE;
import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SessionLoginService sessionLoginService;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid@RequestBody SaveRequest request){
        userService.saveUser(request);
        return CREATE;
    }

    @DeleteMapping
    @LoginCheck
    public ResponseEntity<Void> userDelete(@RequestBody PasswordRequest request,
                                           @CurrentUser String email){
        String  password = request.getPassword();
        userService.deleteUser(email,password);
        sessionLoginService.logout();
        return OK;
    }

    /**
     * 로그인
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public void login(@RequestBody UserDto.LoginRequest loginRequest) {
        sessionLoginService.login(loginRequest);
    }

    /**
     * 로그 아웃
     * @return
     */
    @LoginCheck
    @DeleteMapping("/logout")
    public void logout() {
        sessionLoginService.logout();
    }
    /**
     * 내 정보
     * @param email
     * @return
     */
    @LoginCheck
    @GetMapping("/my-infos")
    public ResponseEntity<UserDto.UserInfoDto> myPage(@CurrentUser String email) {
        UserDto.UserInfoDto loginUser = sessionLoginService.getCurrentUser(email);
        return ResponseEntity.ok(loginUser);
    }


}
