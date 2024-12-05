package com.example.truyen.Repository;

import com.example.truyen.Entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {
    List<Comic> findByTitleContaining(String keyword);
}
