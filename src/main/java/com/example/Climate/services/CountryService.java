package com.example.Climate.services;

import com.example.Climate.dto.CountryDTO;
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

    public List<Country> getAllCountries(String type, String order) {

        return switch (type) {
            case "population" -> order.equalsIgnoreCase("asc")
                    ? repo.getCountryOrderByPopulationASC()
                    : repo.getCountryOrderByPopulationDESC();
            case "temperature" -> order.equalsIgnoreCase("asc")
                    ? repo.getCountryOrderByTemperatureASC()
                    : repo.getCountryOrderByTemperatureDESC();
            default -> order.equalsIgnoreCase("asc")
                    ? repo.getCountryOrderByNameASC()
                    : repo.getCountryOrderByNameDESC();
        };
    }


}
