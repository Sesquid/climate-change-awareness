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
@RequestMapping("/api/City")
public class CityController {
    @Autowired
    CityService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCities() {
        List<City> cityList = service.getAllCities();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCityByCountryName(@RequestParam("country-name") String countryname) {
        List<City> cityList = service.getCityByCountryName(countryname);
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }
}