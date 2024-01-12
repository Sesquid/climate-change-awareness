package com.example.Climate.controllers;

import com.example.Climate.models.Temperature;
import com.example.Climate.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/temp")
public class TemperatureController {
    @Autowired
    TemperatureService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTemp() {
        return new ResponseEntity<>(service.getAllTemp(), HttpStatus.OK);
    }

    @GetMapping("/year-range")
    public ResponseEntity<?> getMinAndMaxYear() {
        Object yearRange = service.getMinAndMaxYear();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getTempListByCoutryName(@RequestParam String countryName) {
        List<Temperature> tempList = service.getTempByCountry(countryName);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    @GetMapping("/all-countries/order-by-temperature/asc")
    public ResponseEntity<?> getAllCountriesOrderByTemperatureAsc(){
        List<String> countryList = service.getAllCountriesOrderByTemperatureAsc();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }
    @GetMapping("/all-countries/order-by-temperature/desc")
    public ResponseEntity<?> getAllCountriesOrderByTemperatureDesc(){
        List<String> countryList = service.getAllCountriesOrderByTemperatureDesc();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

}
