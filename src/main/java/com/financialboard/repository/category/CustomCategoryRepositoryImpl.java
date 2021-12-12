package com.financialboard.repository.category;

import com.financialboard.dto.PostDto.PostList;
import com.financialboard.model.post.Post;
import com.financialboard.model.post.QPost;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.financialboard.model.category.QCategory.category;
import static com.financialboard.model.post.QPost.post;

@RequiredArgsConstructor
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<PostList> getPosts(Pageable pageable, PostList postList) {
        return null;
    }

    @Override
    public Page<PostList> maxPostCategory(Pageable pageable, PostList postList) {
        return null;
    }
}
