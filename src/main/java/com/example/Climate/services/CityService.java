package com.example.Climate.services;

import com.example.Climate.exception.QueryDataException;
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
        List<City> cityList = repo.findAll();
        if (cityList.isEmpty()) {
            throw new QueryDataException("All cities list is empty!");
        }
        return cityList;
    }

    public List<City> getCityByCountryName(String countryname) {
        List<City> cityList = repo.getCityByCountryName(countryname);
        if (cityList.isEmpty()) {
            throw new QueryDataException("Cities list by country name is empty!");
        }
        return cityList;
    }
}
