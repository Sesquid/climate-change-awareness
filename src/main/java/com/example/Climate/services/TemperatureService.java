package com.example.Climate.services;

import com.example.Climate.models.Temperature;
import com.example.Climate.models.YearRange;
import com.example.Climate.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
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

    public YearRange findYearRange() {
        Tuple yearRange = repo.findYearRange();
        return new YearRange(yearRange.get("start", Integer.class), yearRange.get("end", Integer.class));
    }

    public List<String> getAllCountriesOrderByTemperature(String order) {
        return order.equals("ASC") ? repo.getAllCountriesOrderByTemperatureAsc() : repo.getAllCountriesOrderByTemperatureDesc();
    }

}
