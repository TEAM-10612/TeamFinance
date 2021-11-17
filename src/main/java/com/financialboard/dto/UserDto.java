package com.financialboard.dto;

import com.financialboard.model.user.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class UserDto {

    @Getter
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
}
