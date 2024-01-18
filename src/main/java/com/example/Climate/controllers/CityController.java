package com.example.Climate.controllers;

import com.example.Climate.models.City;
import com.example.Climate.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    CityService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCities() {
        List<City> cityList = service.getAllCities();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getCityByCountryName(@RequestParam("countryName") String countryName) {
        List<City> cityList = service.getCityByCountryName(countryName);
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/number-of-cities")
    public ResponseEntity<?> getNumberOfCities() {
        int numberOfCities = service.getNumberOfCities();
        return new ResponseEntity<>(numberOfCities, HttpStatus.OK);
    }
}
