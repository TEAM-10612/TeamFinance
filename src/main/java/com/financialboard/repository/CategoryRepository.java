package com.financialboard.repository;

import com.financialboard.model.category.Category;
import com.financialboard.repository.category.CustomCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> , CustomCategoryRepository {
    Optional<Category>findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
}
