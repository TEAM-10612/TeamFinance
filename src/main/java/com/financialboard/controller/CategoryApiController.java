package com.financialboard.controller;

import com.financialboard.annotation.LoginCheck;

import com.financialboard.exception.category.NotFoundCategoryException;
import com.financialboard.model.category.Category;
import com.financialboard.model.user.UserLevel;
import com.financialboard.repository.CategoryRepository;
import com.financialboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;
import java.util.Optional;

import static com.financialboard.util.ResponseConstants.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryApiController {

}
