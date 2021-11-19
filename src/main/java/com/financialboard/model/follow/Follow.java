package com.financialboard.model.follow;

import com.financialboard.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

    @Id@GeneratedValue
    private Long id;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

}
