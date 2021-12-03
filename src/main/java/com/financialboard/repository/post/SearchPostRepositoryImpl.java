package com.financialboard.repository.post;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.PostDto.SearchPostResponse;
import com.financialboard.model.post.Post;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.financialboard.model.post.QPost.post;

@RequiredArgsConstructor
public class SearchPostRepositoryImpl implements SearchPostRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SearchPostResponse> searchPostList() {
       QueryResults<SearchPostResponse>results = jpaQueryFactory
               .select(Projections.fields(SearchPostResponse.class,
                post.id,
                post.author,
                post.title,
                post.content,
                post.comments
               ))
               .from(post)
               .groupBy(post)
               .fetchResults();

       List<SearchPostResponse> postResponseList = results.getResults();
       return postResponseList;
    }

    @Override
    public List<PostDto.PostResponse> latestSortPost() {
        QueryResults<PostDto.PostResponse> results = jpaQueryFactory
                .select(Projections.fields(PostDto.PostResponse.class,
                        post.id,
                        post.author,
                        post.title,
                        post.content,
                        post.category,
                        post.likesList,
                        post.comments))
                .orderBy(post.id.desc())
                .fetchResults();

        List<PostDto.PostResponse> postResponseList = results.getResults();
        return postResponseList;
    }

    @Override
    public List<PostDto.PostResponse> sortMaxComment() {
        QueryResults<PostDto.PostResponse> results = jpaQueryFactory
                .select(Projections.fields(PostDto.PostResponse.class,
                        post.id,
                        post.author,
                        post.title,
                        post.content,
                        post.category,
                        post.likesList,
                        post.comments))
                .orderBy(post.comments.size().desc())
                .fetchResults();

        List<PostDto.PostResponse> commentDescPost = results.getResults();
        return commentDescPost;
    }

    @Override
    public List<PostDto.PostResponse> sortMaxLikes() {
        QueryResults<PostDto.PostResponse> results = jpaQueryFactory
                .select(Projections.fields(PostDto.PostResponse.class,
                        post.id,
                        post.author,
                        post.title,
                        post.content,
                        post.category,
                        post.likesList,
                        post.comments))
                .orderBy(post.likesList.size().desc())
                .fetchResults();

        List<PostDto.PostResponse> likeDescPost = results.getResults();
        return likeDescPost;
    }

}
