package com.example.Climate.controllers;

import com.example.Climate.models.Country;
import com.example.Climate.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    CountryService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCountries() {
        List<Country> countryList = service.getAllCountries();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/number-of-countries")
    public ResponseEntity<?> getNumberOfCountries() {
        int numberOfCountries = service.getNumberOfCountries();
        return new ResponseEntity<>(numberOfCountries, HttpStatus.OK);
    }
}
