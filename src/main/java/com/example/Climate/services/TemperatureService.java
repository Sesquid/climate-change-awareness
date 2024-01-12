package com.example.Climate.services;

import com.example.Climate.models.Temperature;
import com.example.Climate.models.YearRange;
import com.example.Climate.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {
    @Autowired
    TemperatureRepository repo;

    public List<Temperature> getAllTemp() {
        return repo.findAll();
    }

    public List<Temperature> getTempByCountry(String countryName) {
        return repo.getTempByCountry(countryName);
    }

    public List<Temperature> getTempByCity(String countryName, String cityName) {
        return repo.getTempByCountryAndCity(countryName, cityName);
    }

    public YearRange getMinAndMaxYear() {
        return new YearRange(repo.findMinYear(), repo.findMaxYear());
    }
    public List<String> getAllCountriesOrderByTemperatureAsc(){
        return repo.getAllCountriesOrderByTemperatureAsc();
    }
    public List<String> getAllCountriesOrderByTemperatureDesc(){
        return repo.getAllCountriesOrderByTemperatureDesc();
    }
}
