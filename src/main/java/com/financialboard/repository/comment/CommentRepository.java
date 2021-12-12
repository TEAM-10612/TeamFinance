package com.financialboard.repository.comment;

import com.financialboard.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment ,Long> ,CustomCommentRepository{
}
