package com.financialboard.repository;

import com.financialboard.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findByBranch(String branch);
    Optional<Category> findByBranchAndCode(String branch,String name);

    Boolean existsByBranchAndName(String branch,String name);

    @Query(value = "SELECT MAX(c.level) FROM Category c WHERE c.branch = :branch")
    Long maxLevel (@Param("branch") String branch);

    int deleteByBranchAndCode (String branch, String code);
}
