package com.financialboard.controller;

import com.financialboard.dto.UserDto;
import com.financialboard.dto.UserDto.SaveRequest;
import com.financialboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import static com.financialboard.util.ResponseConstants.CREATE;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid@RequestBody SaveRequest request){
        userService.saveUser(request);
        return CREATE;
    }
}
