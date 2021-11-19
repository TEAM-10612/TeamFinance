package com.financialboard.model.user;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UserBase extends BaseTimeEntity{

    @Id@GeneratedValue
    @Column(name = "user_id")
    private Long id;

    protected String email;

    protected String password;

    @Enumerated(EnumType.STRING)
    protected UserLevel userLevel;

    @Enumerated(EnumType.STRING)
    protected UserGrade userGrade;
}
