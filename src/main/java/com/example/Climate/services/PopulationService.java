package com.example.Climate.services;

import com.example.Climate.exception.QueryDataException;
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
        List<Population> populationList = repo.findAll();
        if (populationList.isEmpty()) {
            throw new QueryDataException("All Population list is empty!");
        }
        return populationList;
    }
    public List<Population> getWorldPopulation() {
        List<Population> populationList = repo.getWorldPopulation();
        if(populationList.isEmpty()) {
            throw new QueryDataException("World Population list is empty!");
        }
        return populationList;
    }

    public List<Population> getAllCountriesPopulation(int year) {
        List<Population> populationList = repo.getAllCountriesPopulationByYear(year);
        if (populationList.isEmpty()) {
            throw new QueryDataException("All country population list by year is empty!");
        }
        return populationList;
    }

    public Object getMinAndMaxYear() {
        Object yearRange = repo.findMinAndMaxYear();
        return yearRange;
    }
}
