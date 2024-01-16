package com.example.Climate.services;

import com.example.Climate.models.Population;
import com.example.Climate.models.YearRange;
import com.example.Climate.repositories.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
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
        Tuple yearRange = repo.findYearRange();
        return new YearRange(yearRange.get("start", Integer.class), yearRange.get("end", Integer.class));
    }

    public List<Population> getPopulationListByCountryName(String countryName) {
        return repo.getPopulationListByCountryName(countryName);
    }

    public List<String> getAllCountriesOrderByPopulation(String order) {
        return order.equals("ASC") ? repo.getAllCountriesOrderByPopulationAsc() : repo.getAllCountriesOrderByPopulationDesc();
    }

    public Long getPopulationDifference(String countryName, int startYear, int endYear) {
        return repo.getPopulationDifference(countryName, startYear, endYear);
    }
}
