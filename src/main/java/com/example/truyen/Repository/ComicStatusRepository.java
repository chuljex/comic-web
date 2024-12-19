package com.example.truyen.Repository;

import com.example.truyen.Entity.ComicStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComicStatusRepository extends JpaRepository<ComicStatus, Long> {
    Optional<ComicStatus> findById(Long id);
}
