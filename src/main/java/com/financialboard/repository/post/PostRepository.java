package com.financialboard.repository.post;

import com.financialboard.model.post.Post;
import com.financialboard.model.user.User;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post>findById(long id);
}
