package com.financialboard.repository.comment;

import com.financialboard.dto.CommentDto;
import com.financialboard.dto.CommentDto.ListComments;

import java.util.List;

public interface CustomCommentRepository {

    List<ListComments> latestSortComment();
}
