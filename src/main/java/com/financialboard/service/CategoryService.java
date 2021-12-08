package com.financialboard.service;


import com.financialboard.dto.CategoryDto;
import com.financialboard.exception.category.ExistsBranchAndNameException;
import com.financialboard.exception.category.NotFoundBranchException;
import com.financialboard.exception.category.NotFoundCategoryException;
import com.financialboard.exception.category.NotParentCategoryException;
import com.financialboard.model.category.Category;
import com.financialboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.financialboard.dto.CategoryDto.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

}
