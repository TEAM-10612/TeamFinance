package com.financialboard.repository;

import com.financialboard.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findUserById(Long id);
    boolean existByEmail(String email);
    boolean existByNickname(String nickname);
    boolean existByEmailAndPassword(String email, String password);
}
