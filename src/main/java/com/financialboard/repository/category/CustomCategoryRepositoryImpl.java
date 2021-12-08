package com.financialboard.repository.category;

import com.financialboard.dto.PostDto.PostList;
import com.financialboard.model.post.Post;
import com.financialboard.model.post.QPost;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.financialboard.model.category.QCategory.category;
import static com.financialboard.model.post.QPost.post;

@RequiredArgsConstructor
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<PostList> getPosts() {
        QueryResults<PostList> results = jpaQueryFactory
                .select(Projections.fields(PostList.class,
                        post.id,
                        post.author,
                        post.title,
                        post.category
                        ))
                .from(category)
                .leftJoin(category.posts, post)
                .groupBy(post.id)
                .orderBy(
                       post.id.desc()
                )
                .fetchResults();

        List<PostList> postLists = results.getResults();

        return postLists;
    }


}
