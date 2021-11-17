package com.financialboard.repository;

import com.financialboard.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findUserById(Long id);
    boolean existByEmail(String email);
    boolean existByNickname(String nickname);
}
