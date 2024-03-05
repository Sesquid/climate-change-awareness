package com.example.Climate.services;

import com.example.Climate.dto.ComparePopulation;
import com.example.Climate.dto.YearRange;
import com.example.Climate.models.Country;
import com.example.Climate.models.Population;
import com.example.Climate.repositories.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopulationService {
    @Autowired
    PopulationRepository repo;

    public List<Population> getAllPopulation() {
        return repo.findAll();
    }

    public List<Population> getWorldPopulation() {
        return repo.getWorldPopulation();
    }

    public List<Population> getAllCountriesPopulation(int year) {
        return repo.getAllCountriesPopulationByYear(year);
    }

    public YearRange findYearRange() {
        return repo.findPopulationYearRange();
    }

    public List<Population> getPopulationListByCountryCode(String countryCode) {
        return repo.getPopulationListByCountryCode(countryCode);
    }


    public ComparePopulation getPopulationDifference(String countryCode, int startYear, int endYear) {
        return repo.findPopulationDifference(countryCode, startYear, endYear);
    }

//    public List<Population> getCountryPopulationByYearRange(String countryCode, int startYear, int endYear) {
//        return repo.getCountryPopulationByYearRange(countryCode, startYear, endYear);
//    }
}
