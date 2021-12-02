package com.financialboard.repository;

import com.financialboard.model.post.Post;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Post findByPostId(Long id);
    Optional<Post>findById(Long id);
}
