package com.example.truyen.Repository;

import com.example.truyen.Entity.Country;
import com.example.truyen.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findById(Long id);
}
