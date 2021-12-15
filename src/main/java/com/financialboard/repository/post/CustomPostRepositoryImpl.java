package com.financialboard.repository.post;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.PostDto.SearchPostResponse;
import com.financialboard.model.post.PostStandard;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.financialboard.model.category.QCategory.category;
import static com.financialboard.model.post.QPost.post;
import static com.financialboard.model.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<SearchPostResponse> searchPostList(boolean useSearchBtn,Pageable pageable, PostDto.SearchCondition condition) {
        QueryResults<SearchPostResponse>results = jpaQueryFactory
                .select(Projections.fields(SearchPostResponse.class,
                        post.id,
                        post.author,
                        post.title,
                        post.category,
                        post.likesList,
                        post.comments
                        ))
                .from(post)
                .where(
                        containsKeyword(condition.getKeyword())
                ).orderBy(
                        getOrderSpecifier(condition.getOrderStandard())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<SearchPostResponse> postResponseList = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(postResponseList,pageable,total);
    }

    @Override
    public Page<SearchPostResponse> getCategoryByPost(Pageable pageable, Long id) {
        QueryResults<SearchPostResponse> results = jpaQueryFactory
                .select(Projections.fields(SearchPostResponse.class,
                        post.id,
                        post.title,
                        post.author,
                        post.category,
                        post.comments,
                        post.likesList
                ))
                .from(post)
                .where(
                        getCategoryId(id)
                )
                .orderBy(post.createTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SearchPostResponse> postResponses = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(postResponses,pageable,total);

    }

    @Override
    public Page<SearchPostResponse> postBySort(Pageable pageable, PostStandard postStandard) {
        QueryResults<SearchPostResponse> results = jpaQueryFactory
                .select(Projections.fields(SearchPostResponse.class,
                        post.id,
                        post.title,
                        post.author,
                        post.category,
                        post.comments,
                        post.likesList
                ))
                .from(post)
                .orderBy(
                        getOrderSpecifier(postStandard)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<SearchPostResponse> responses = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(responses,pageable,total);
    }

    private OrderSpecifier getOrderSpecifier(PostStandard postStandard){
        OrderSpecifier orderSpecifier = null;

        if (postStandard == null){
            orderSpecifier = new OrderSpecifier(Order.DESC,post.createTime);
        }else{
            switch (postStandard){
                case HIGH_LIKES:
                    orderSpecifier = new OrderSpecifier(Order.DESC,post.likesList.size());
                    break;
                case HIGH_COMMENT:
                    orderSpecifier = new OrderSpecifier(Order.DESC,post.comments.size());
                case RELEASE_DATE:
                    orderSpecifier = new OrderSpecifier(Order.DESC,post.createTime);
                default:
                    orderSpecifier = new OrderSpecifier(Order.DESC,post.createTime);
            }
        }

        return orderSpecifier;
    }

    private BooleanExpression containsKeyword(String keyword) {
        return (hasText(keyword)) ? post.title.containsIgnoreCase(keyword)
                .or(post.title.containsIgnoreCase(keyword))
                : null;
    }

    private BooleanExpression getCategoryId(Long categoryId){
        return categoryId != null ? category.id.eq(categoryId) : null;
    }
}
