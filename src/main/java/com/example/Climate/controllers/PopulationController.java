package com.example.Climate.controllers;

import com.example.Climate.models.Population;
import com.example.Climate.services.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Population")
public class PopulationController {
    @Autowired
    PopulationService service;

    public ResponseEntity<?> getAllPopulation() {
        List<Population> populationList = service.getAllPopulation();
        return new ResponseEntity<>(service.getAllPopulation(), HttpStatus.OK);
    }
}
