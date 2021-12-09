package com.financialboard.controller;

import com.financialboard.model.category.Category;
import com.financialboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.financialboard.dto.CategoryDto.*;
import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid@RequestBody SaveRequest request){
        categoryService.createCategory(request);
        return OK;
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable(name = "id") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PatchMapping("/{id}")
    public void updateCategory(@PathVariable(name = "id") Long id,
                               @Valid@RequestBody SaveRequest request){
        categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(name = "id")Long id){
        categoryService.deleteCategory(id);
    }
}
