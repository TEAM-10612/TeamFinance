package com.financialboard.repository;

import com.financialboard.model.likes.Likes;
import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long>{
    Optional<Likes> findByUserAndPost(User user, Post post);
}
