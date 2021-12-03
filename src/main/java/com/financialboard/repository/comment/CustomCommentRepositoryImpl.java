package com.financialboard.repository.comment;

import com.financialboard.dto.CommentDto;
import com.financialboard.dto.CommentDto.ListComments;
import com.financialboard.dto.PostDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.financialboard.model.comment.QComment.comment;
import static com.financialboard.model.post.QPost.post;

@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ListComments> latestSortComment() {
        QueryResults<ListComments> results = jpaQueryFactory
                .select(Projections.fields(ListComments.class,
                        comment.id,
                        comment.content,
                        comment.author,
                        comment.post
                ))
                .orderBy(comment.id.desc())
                .fetchResults();
        List<ListComments> result = results.getResults();
        return result;
    }
}

