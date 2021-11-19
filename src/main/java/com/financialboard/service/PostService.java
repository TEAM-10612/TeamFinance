package com.financialboard.service;

import com.financialboard.dto.PostDto;
import com.financialboard.dto.PostDto.SaveRequest;
import com.financialboard.exception.PostNotFoundException;
import com.financialboard.model.post.Post;
import com.financialboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(SaveRequest request, @Nullable MultipartFile multipartFile){
        if (multipartFile != null){
            String imagePath = FileService.upload(multipartFile);
            request.setPostImgUrl(imagePath);
        }

        postRepository.save(request.toEntity());
    }
    @Transactional(readOnly = true)
    public PostDto.PostInfoResponse getPostInfo(Long id){
        return postRepository.findById(id).orElseThrow(
                PostNotFoundException::new
        ).toPostInfo();

    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id, SaveRequest updatePost, @Nullable MultipartFile multipartFile){
        Post savePost = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        String saveImagePath = savePost.getPostImgUrl();
        String updateImagePath = updatePost.getPostImgUrl();

        if (isDeleteSavedImage(saveImagePath,updateImagePath,multipartFile)){
            updatePost.deleteImagePath();
        }
        if(multipartFile != null){
            String updateImage = FileService.upload(multipartFile);
            updatePost.setPostImgUrl(updateImage);
        }

        savePost.update(updatePost);


    }
    private boolean isDeleteSavedImage(String savedImagePath, String updatedImagePath,
                                       MultipartFile productImage) {
        return ((updatedImagePath == null && savedImagePath != null) ||
                (savedImagePath != null && productImage != null));
    }
}
