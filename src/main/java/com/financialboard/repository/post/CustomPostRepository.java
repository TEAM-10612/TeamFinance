package com.financialboard.repository.post;

import com.financialboard.dto.PostDto;

import java.util.List;

public interface CustomPostRepository {

    List<PostDto.SearchPostResponse> searchPostList();

    List<PostDto.PostResponse> latestSortPost();
    List<PostDto.PostResponse> sortMaxComment();
    List<PostDto.PostResponse> sortMaxLikes();
}
