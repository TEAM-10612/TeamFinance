package com.financialboard.repository.category;

import com.financialboard.dto.CategoryDto;
import com.financialboard.dto.CategoryDto.CategoryResponse;

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
    public Page<CategoryResponse> maxPostCategory(Pageable pageable) {
        QueryResults<CategoryResponse> results = jpaQueryFactory
                .select(Projections.fields(CategoryResponse.class,
                        category.id,
                        category.categoryName,
                        category.childCategory
                        ))
                .from(category)
                .orderBy(
                        category.posts.size().desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<CategoryResponse> responses = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(responses,pageable,total);
    }
}
