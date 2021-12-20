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
import javax.validation.constraints.Size;

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


        private String phone;

        public void passwordEncryption(EncryptionService em){
            this.password = em.encrypt(password);
        }
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
                    .userGrade(UserGrade.BRONZE)
                    .userLevel(UserLevel.USER)
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
        private UserLevel userLevel;
        private UserGrade userGrade;

        @Builder
        public UserInfoDto(String email, String nickname,  UserLevel userLevel,UserGrade userGrade) {
            this.email = email;
            this.nickname = nickname;
            this.userGrade = userGrade;
            this.userLevel = userLevel;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class UserInfo{

        private Long id;
        private String email;


        public User toEntity(){
            return User.builder()
                    .id(this.id)
                    .email(this.email)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PasswordRequest{
        private String password;

        @Builder
        public PasswordRequest(String password) {
            this.password = password;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class ChangePasswordRequest{

        private String email;

        @NotBlank(message = "비밀번호를 입력하세요.")
        @Size(min = 8,max = 20,message = "비밀번호는 8자 이상 20자 이하로 입력하세요. ")
        private String passwordAfter;
        private String passwordBefore;

        public void passwordEncryption(EncryptionService encryptionService){
            this.passwordAfter = encryptionService.encrypt(passwordAfter);
            this.passwordBefore = encryptionService.encrypt(passwordBefore);
        }
        @Builder
        public ChangePasswordRequest(String email,
                                     @NotBlank(message = "비밀번호를 입력하세요.") @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력하세요. ") String passwordAfter,
                                     String passwordBefore) {
            this.email = email;
            this.passwordAfter = passwordAfter;
            this.passwordBefore = passwordBefore;
        }
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserPostInfo{
        private Long id;
        private String nickname;
        private UserLevel userLevel;
        private UserGrade userGrade;


        public User toEntity(){
            return User.builder()
                    .id(this.id)
                    .nickname(this.nickname)
                    .userLevel(this.userLevel)
                    .userGrade(this.userGrade)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UserListInfo{
        private Long id;
        private String nickname;
        private String email;
        private UserLevel userLevel;
        private UserGrade userGrade;

        @Builder

        public UserListInfo(Long id, String nickname, String email, UserLevel userLevel, UserGrade userGrade) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.userLevel = userLevel;
            this.userGrade = userGrade;
        }
    }

    @Getter
    public static class UserSearch{
        private Long id;
        private String nickname;
        private String email;
        private UserLevel userLevel;
        private UserGrade userGrade;

        @Builder
        public UserSearch(Long id, String nickname, String email, UserLevel userLevel, UserGrade userGrade) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.userLevel = userLevel;
            this.userGrade = userGrade;
        }
    }
}
