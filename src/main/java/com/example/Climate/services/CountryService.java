package com.example.Climate.services;

import com.example.Climate.models.Country;
import com.example.Climate.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository repo;

    public List<Country> getAllCountries() {
        return repo.findAll();
    }

    public int getNumberOfCountries() {
        return repo.getNumberOfCountries();
    }

    public List<String> getAllCountriesOrderByNameAsc () {
        return repo.getAllCountriesOrderByNameAsc();
    }

    public List<String> getAllCountriesOrderByNameDesc () {
        return repo.getAllCountriesOrderByNameDesc();
    }
}
