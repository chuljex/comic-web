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
        // Lấy danh sách tất cả các country
        var data = countryRepository.findAll();
        return data;
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }
}
