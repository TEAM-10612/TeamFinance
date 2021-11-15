package com.financialboard.model.user;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBase extends BaseTimeEntity{

    @Id@GeneratedValue
    private Long id;

    protected String email;

    protected String password;

    @Enumerated(EnumType.STRING)
    protected UserLevel userLevel;
}
