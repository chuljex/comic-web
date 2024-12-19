package com.example.truyen.Repository;

import com.example.truyen.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE " +
            "(:status IS NULL OR c.status = :status) AND " +
            "(:country IS NULL OR c.country = :country) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'date_desc' THEN c.updatedAt END DESC, " +
            "CASE WHEN :sortBy = 'date_asc' THEN c.updatedAt END ASC")
    List<Category> findCategoriesWithFilters(
            @Param("status") String status,
            @Param("country") String country,
            @Param("sortBy") String sortBy);
}