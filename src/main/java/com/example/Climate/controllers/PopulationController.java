package com.example.Climate.controllers;

import com.example.Climate.models.Population;
import com.example.Climate.models.RegionInformation;
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

    @GetMapping("/all-countries-population")
    public ResponseEntity<?> getAllCountriesPopulationByYear(@RequestParam("year") int year) {
        List<Population> populationList = service.getAllCountriesPopulation(year);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/year-range")
    public ResponseEntity<?> findYearRange() {
        YearRange years = service.findYearRange();
        return new ResponseEntity<>(years, HttpStatus.OK);
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getPopulationListByCountryName(@RequestParam("countryName") String countryName) {
        List<Population> populationList = service.getPopulationListByCountryName(countryName);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/all-countries/order-by-population")
    public ResponseEntity<?> getAllCountriesOrderByPopulation(@RequestParam("order") String order) {
        List<String> countryList = service.getAllCountriesOrderByPopulationIn2013(order);
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/diff")
    public ResponseEntity<?> getPopulationDifference(@RequestBody RegionInformation region) {
        Long diff = service.getPopulationDifference(region);
        return new ResponseEntity<>(diff, HttpStatus.OK);
    }

    @GetMapping("/by-year-range")
    public ResponseEntity<?> getCountryPopulationByYearRange(@RequestBody RegionInformation region) {
        List<Population> populationList = service.getCountryPopulationByYearRange(region);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }
}
