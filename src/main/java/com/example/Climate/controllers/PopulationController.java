package com.example.Climate.controllers;

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

    @GetMapping("/all-countries-population/")
    public ResponseEntity<?> getAllCountriesPopulationByYear(@RequestParam("year") int year) {
        List<Population> populationList = service.getAllCountriesPopulation(year);
        return new ResponseEntity<>(populationList, HttpStatus.OK);
    }

    @GetMapping("/year-range")
    public ResponseEntity<?> getMinAndMaxYear() {
        Object yearRange = service.getMinAndMaxYear();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }
}
