package com.financialboard.model.user;

import lombok.*;
import javax.persistence.Entity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends UserBase {

    private String nickname;
    private String phone;


    @Builder
    public User(Long id, String email, String password, UserLevel userLevel, String nickname, String phone) {
        super(id, email, password, userLevel);
        this.nickname = nickname;
        this.phone = phone;
    }
}
