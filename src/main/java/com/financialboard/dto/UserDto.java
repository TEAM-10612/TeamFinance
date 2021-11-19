package com.financialboard.dto;

import com.financialboard.encryption.EncryptionService;
import com.financialboard.model.user.User;
import com.financialboard.model.user.UserGrade;
import com.financialboard.model.user.UserLevel;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class SaveRequest{

        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Length(min = 6, max = 20,message = "6자 이상 20자 이하로 입력하세요.")
        private String password;

        @NotBlank
        @Length(min = 6,max = 20)
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-z0-9]{3,20}$")
        private String nickname;

        @NotBlank
        @Length(min = 10, max = 11)
        private String phone;

        @Builder
        public SaveRequest(String email, String password, String nickname, String phone) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
            this.phone = phone;
        }

        public User toEntity(){
            return User.builder()
                    .email(this.email)
                    .password(this.password)
                    .nickname(this.nickname)
                    .phone(this.phone)
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor
    public static class LoginRequest{

        private String email;
        private String password;

        public void passwordEncryption(EncryptionService encryptionService){
            this.password = encryptionService.encrypt(password);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UserInfoDto {
        private String email;
        private String nickname;
        private String phone;
        private UserLevel userLevel;
        private UserGrade userGrade;

        @Builder
        public UserInfoDto(String email, String nickname, String phone, UserLevel userLevel,UserGrade userGrade) {
            this.email = email;
            this.nickname = nickname;
            this.phone = phone;
            this.userGrade = userGrade;
            this.userLevel = userLevel;
        }
    }
}
