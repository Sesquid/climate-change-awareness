package com.example.Climate.controllers;

import com.example.Climate.models.Country;
import com.example.Climate.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    CountryService service;

    @GetMapping("/number-of-countries")
    public ResponseEntity<?> getNumberOfCountries() {
        int numberOfCountries = service.getNumberOfCountries();
        return new ResponseEntity<>(numberOfCountries, HttpStatus.OK);
    }

    @GetMapping("/get-all/order-by-name")
    public ResponseEntity<?> getAllCountriesOrderByName(@RequestParam String order) {
        List<String> countryList = service.getAllCountriesOrderByName(order);
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

}
