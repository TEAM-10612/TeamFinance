package com.financialboard.repository;

import com.financialboard.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment ,Long> {
}
