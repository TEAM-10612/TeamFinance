package com.financialboard.repository.admin;


import com.financialboard.dto.UserDto;
import com.financialboard.dto.UserDto.UserListInfo;
import com.financialboard.model.user.UserGrade;
import com.financialboard.model.user.UserLevel;
import com.financialboard.service.LikesService;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.financialboard.model.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class AdminRepositoryImpl  implements AdminRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<UserListInfo> searchByUser(UserDto.UserSearch search, Pageable pageable) {
        QueryResults<UserListInfo> results = jpaQueryFactory
                .select(Projections.fields(UserListInfo.class,
                        user.id,
                        user.nickname,
                        user.email,
                        user.userGrade))
                .from(user)
                .where(
                        userEmailEq(search.getEmail()),
                        userIdEq(search.getId()),
                        userGradeEq(search.getUserGrade()),
                        userLevelEq(search.getUserLevel()),
                        userNicknameEq(search.getNickname())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();


        List<UserListInfo> userListInfos = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(userListInfos, pageable, total);
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanExpression userNicknameEq(String userNickname){
        return hasText(userNickname) ? user.nickname.endsWith(userNickname) : null;
    }
    private BooleanExpression userEmailEq(String userEmail) {
        return hasText(userEmail) ? user.email.endsWith(userEmail) : null;
    }
    private BooleanExpression userLevelEq(UserLevel userLevel) {
        return userLevel != null ? user.userLevel.eq(userLevel) : null;
    }

    private BooleanExpression userGradeEq(UserGrade userGrade) {
        return userGrade != null ? user.userGrade.eq(userGrade) : null;
    }
}
