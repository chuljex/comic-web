package com.example.truyen.Repository;

import com.example.truyen.Entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {
    List<Comic> findByViewsGreaterThanOrderByViewsDesc(int views);

    List<Comic> findByTitleContainingIgnoreCase(String title);

    Optional<Comic> findById(Long id);

    @Query("SELECT c FROM Comic c " +
            "JOIN c.comicStatus cs " +
            "JOIN c.country co " +
            "JOIN ComicCategory cc ON cc.comic.id = c.id " +
            "WHERE cc.category.id = :categoryId " +
            "AND (:status IS NULL OR cs.id = :status) " +
            "AND (:country IS NULL OR co.id = :country) " +
            "ORDER BY c.updatedAt DESC")
    List<Comic> findByFilters(
            @Param("categoryId") Long categoryId,
            @Param("status") Integer status,
            @Param("country") Integer country
    );
}
