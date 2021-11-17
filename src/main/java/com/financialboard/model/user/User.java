package com.financialboard.model.user;

import com.financialboard.dto.UserDto;
import com.financialboard.model.comment.Comment;
import com.financialboard.model.post.Post;
import lombok.*;
import net.bytebuddy.pool.TypePool;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends UserBase {

    private String nickname;

    private String phone;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public User(Long id, String email, String password, UserLevel userLevel, String nickname, String phone) {
        super(id, email, password, userLevel);
        this.nickname = nickname;
        this.phone = phone;
    }


    public UserDto.UserInfoDto toUserInfoDto(){
        return UserDto.UserInfoDto.builder()
                .email(this.getEmail())
                .nickname(this.getNickname())
                .phone(this.getPhone())
                .userLevel(this.userLevel)
                .build();
    }
}
