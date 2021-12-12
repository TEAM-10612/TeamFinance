package com.financialboard.repository.post;

import com.financialboard.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CustomPostRepository {

    Page<PostDto.SearchPostResponse> searchPostList(Pageable pageable, PostDto.SearchPostResponse postResponse);
    Page<PostDto.PostResponse> latestSortPost(Pageable pageable, PostDto.PostResponse postResponse);
    Page<PostDto.PostResponse> sortMaxComment(Pageable pageable, PostDto.PostResponse postResponse);
    Page<PostDto.PostResponse> sortMaxLikes(Pageable pageable, PostDto.PostResponse postResponse);
}
