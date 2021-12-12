package com.financialboard.repository.post;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.PostDto.SearchPostResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<SearchPostResponse> searchPostList(Pageable pageable, SearchPostResponse searchPostResponse) {
        return null;
    }

    @Override
    public Page<PostDto.PostResponse> latestSortPost(Pageable pageable, PostDto.PostResponse postResponse) {
        return null;
    }

    @Override
    public Page<PostDto.PostResponse> sortMaxComment(Pageable pageable, PostDto.PostResponse postResponse) {
        return null;
    }

    @Override
    public Page<PostDto.PostResponse> sortMaxLikes(Pageable pageable, PostDto.PostResponse postResponse) {
        return null;
    }
}
