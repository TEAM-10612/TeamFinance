package com.financialboard.controller;

import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.CategoryDto.CategoryInfo;
import com.financialboard.dto.CategoryDto.CategoryReturnDto;
import com.financialboard.exception.category.ExistsCategoryNameException;
import com.financialboard.exception.category.NotFoundCategoryException;
import com.financialboard.model.category.Category;
import com.financialboard.repository.CategoryRepository;
import com.financialboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryApiController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<CategoryInfo>saveCategory(
            @Valid@RequestBody CategoryInfo request){
        String requestName = request.getName();
        Optional<Category> name = categoryRepository.findByName(requestName);
        if (name != null){
            throw new ExistsCategoryNameException();
        }
        categoryService.saveCategory(request);

        return ResponseEntity.ok(request);
    }

    @LoginCheck
    @DeleteMapping("/{branch}/{code}")
    public ResponseEntity<Void> deleteCategory(@RequestBody String branch,@RequestBody String code){
        Optional<Category> category = categoryRepository.findByBranchAndCode(branch, code);
        if (category.isEmpty()){
            throw new NotFoundCategoryException("삭제할 수 있는 카테고리가 존재하지 않습니다.");
        }
        categoryService.deleteCategory(branch,code);
        return OK;
    }

    @GetMapping("/{branch}")
    @LoginCheck
    public ResponseEntity<CategoryReturnDto> getCategory(@Valid@RequestBody String branch){
        Optional<Category> findBranch = categoryRepository.findByBranch(branch);
        if(findBranch.isEmpty()){
            throw new NotFoundCategoryException("조회할 수 있는 카테고리가 존재하지 않습니다.");
        }
        CategoryReturnDto category = categoryService.getCategoryBranch(branch);
        return  ResponseEntity.ok(category);
    }

    @PutMapping("/{branch}/{code}")
    @LoginCheck
    public ResponseEntity<Long> updateCategory(@RequestBody String branch, @RequestBody String code, @RequestBody@Valid CategoryInfo request){
        Optional<Category> category = categoryRepository.findByBranchAndCode(branch, code);
        if (category.isEmpty()){
            throw new NotFoundCategoryException("삭제할 수 있는 카테고리가 존재하지 않습니다.");
        }

        Long updateCategory = categoryService.updateCategory(branch, code, request);
        return ResponseEntity.ok(updateCategory);
    }
}
