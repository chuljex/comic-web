package com.example.truyen.Service;

import com.example.truyen.Entity.Country;
import com.example.truyen.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountry() {
        try {
            var data = countryRepository.findAll();

            if (data.isEmpty()) {
                throw new RuntimeException("No data found");
            }

            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }
}
