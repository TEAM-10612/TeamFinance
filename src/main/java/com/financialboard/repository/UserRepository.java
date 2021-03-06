package com.financialboard.repository;

import com.financialboard.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional <User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByEmailAndPassword(String email, String password);
    void deleteByEmail(String email);
}
