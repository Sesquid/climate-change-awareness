package com.example.Climate.services;

import com.example.Climate.models.Country;
import com.example.Climate.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Country> getAllCountries(String order) {
        List<Country> countryList;
        if (order.equalsIgnoreCase("ASC"))
            countryList = repo.findAll(Sort.by(Sort.Direction.ASC, "countryName"));
        else
            countryList = repo.findAll(Sort.by(Sort.Direction.DESC, "countryName"));
        return countryList;
    }


}
