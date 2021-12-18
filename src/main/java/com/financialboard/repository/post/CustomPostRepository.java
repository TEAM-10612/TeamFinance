package com.financialboard.repository.post;

import com.financialboard.dto.PostDto;
import com.financialboard.model.post.PostStandard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CustomPostRepository {

    Page<PostDto.SearchPostResponse> searchPostList(boolean useSearchBtn,Pageable pageable, PostDto.SearchCondition postResponse);
    Page<PostDto.SearchPostResponse> postBySort(Pageable pageable, PostStandard postStandard);
}
