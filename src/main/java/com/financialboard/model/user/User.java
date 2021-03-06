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

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public User(Long id, String email, String password, UserLevel userLevel,UserGrade userGrade, String nickname, String phone) {
        super(id, email, password, phone,userLevel,userGrade);
        this.nickname = nickname;
    }


    public UserDto.UserInfo toUserInfo(){
        return UserDto.UserInfo.builder()
                .id(this.getId())
                .email(this.getEmail())
                .build();
    }


    public void updatePassword(String password){
        this.password = password;
    }
}
