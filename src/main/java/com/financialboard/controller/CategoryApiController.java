package com.financialboard.controller;

import com.financialboard.annotation.LoginCheck;
import com.financialboard.dto.CategoryDto;
import com.financialboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto.CategoryInfo>saveCategory(
            @Valid@RequestBody CategoryDto.CategoryInfo request){
        categoryService.saveCategory(request);

        return ResponseEntity.ok(request);
    }

    @LoginCheck
    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@RequestBody String branch,@RequestBody String code){
            categoryService.deleteCategory(branch,code);
            return OK;
    }

    @GetMapping
    @LoginCheck
    public ResponseEntity<CategoryDto.CategoryInfo> getCategory(){

    }

    @PatchMapping
    @LoginCheck
    public ResponseEntity<CategoryDto.CategoryInfo> updateCategory(){

    }
}
