package com.example.Climate.services;

import com.example.Climate.models.City;
import com.example.Climate.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository repo;

    public List<City> getAllCities() {
        return repo.findAll();
    }

    public List<City> getCityByCountryCode(String countryCode) {
        return repo.getCityByCountryCode(countryCode);
    }

    public int getNumberOfCities() {
        return repo.getNumberOfCities();
    }
}
