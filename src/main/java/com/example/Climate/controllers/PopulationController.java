package com.example.Climate.controllers;

import com.example.Climate.dto.ComparePopulation;
import com.example.Climate.dto.YearRange;
import com.example.Climate.models.Population;
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

    @GetMapping("/get-year-range")
    public ResponseEntity<?> findYearRange() {
        YearRange years = service.findYearRange();
        return new ResponseEntity<>(years, HttpStatus.OK);
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getPopulationListByCountryCode(@RequestParam("countryCode") String countryCode) {
        List<Population> populationList = service.getPopulationListByCountryCode(countryCode);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/diff-by-years")
    public ResponseEntity<?> getPopulationDifference(@RequestParam("countryCode") String countryCode,
                                                     @RequestParam("startYear") int startYear,
                                                     @RequestParam("endYear") int endYear) {
        ComparePopulation diff = service.getPopulationDifference(countryCode, startYear, endYear);
        return new ResponseEntity<>(diff, HttpStatus.OK);
    }

//    @GetMapping("/by-year-range")
//    public ResponseEntity<?> getCountryPopulationByYearRange(@RequestParam("countryCode") String countryCode,
//                                                             @RequestParam("startYear") int startYear,
//                                                             @RequestParam("endYear") int endYear) {
//        List<Population> populationList = service.getCountryPopulationByYearRange(countryCode, startYear, endYear);
//        return new ResponseEntity<>(populationList, HttpStatus.OK);
//    }
}
