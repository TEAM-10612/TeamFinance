package com.financialboard.repository.category;

import com.financialboard.dto.PostDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
public interface CustomCategoryRepository {

    List<PostDto.PostList> getPosts();
}
