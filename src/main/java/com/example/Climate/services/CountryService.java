package com.example.Climate.services;

import com.example.Climate.exception.QueryDataException;
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
        List<Country> countryList = repo.findAll();
        if (countryList.isEmpty()) {
            throw new QueryDataException("All countries list is empty!");
        }
        return countryList;
    }


}
