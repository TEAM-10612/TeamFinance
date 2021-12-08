package com.financialboard.controller;

import com.financialboard.dto.CategoryDto;
import com.financialboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.financialboard.dto.CategoryDto.*;
import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid@RequestBody SaveRequest request){
        categoryService.createCategory(request);
        return OK;
    }
}
