package com.example.Climate.controllers;

import com.example.Climate.models.Population;
import com.example.Climate.models.YearRange;
import com.example.Climate.services.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/population")
public class PopulationController {
    @Autowired
    PopulationService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPopulation() {
        List<Population> populationList = service.getAllPopulation();
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/world-population")
    public ResponseEntity<?> getWorldPopulation() {
        List<Population> populationList = service.getWorldPopulation();
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/all-countries-population/")
    public ResponseEntity<?> getAllCountriesPopulationByYear(@RequestParam("year") int year) {
        List<Population> populationList = service.getAllCountriesPopulation(year);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/year-range")
    public ResponseEntity<?> getMinAndMaxYear() {
        YearRange yearRange = service.getMinAndMaxYear();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getPopulationListByCountryName(@RequestParam String countryName) {
        List<Population> populationList = service.getPopulationListByCountryName(countryName);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/all-countries/order-by-population/asc")
    public ResponseEntity<?> getAllCountriesOrderByPopulationAsc() {
        List<String> countryList = service.getAllCountriesOrderByPopulationAsc();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/all-countries/order-by-population/desc")
    public ResponseEntity<?> getAllCountriesOrderByPopulationDesc() {
        List<String> countryList = service.getAllCountriesOrderByPopulationDesc();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/diff")
    public ResponseEntity<?> getPopulationDifference(@RequestParam String countryName, @RequestParam int startYear, @RequestParam int endYear) {
        Long diff = service.getPopulationDifference(countryName, startYear, endYear);
        return new ResponseEntity<>(diff, HttpStatus.OK);
    }
}
