package com.example.Climate.services;

import com.example.Climate.models.Population;
import com.example.Climate.models.YearRange;
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

    public YearRange getMinAndMaxYear() {
        return new YearRange(repo.findMinYear(), repo.findMaxYear());
    }
}
