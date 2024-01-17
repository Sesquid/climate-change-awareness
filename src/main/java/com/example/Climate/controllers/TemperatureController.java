package com.example.Climate.controllers;

import com.example.Climate.DTO.TemperatureDTO;
import com.example.Climate.models.RegionInformation;
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
    public ResponseEntity<?> findYearRange() {
        Object yearRange = service.findYearRange();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getTempListByCountryName(@RequestParam("countryName") String countryName) {
        List<Temperature> tempList = service.getTempByCountry(countryName);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    @GetMapping("/all-countries/order-by-temperature")
    public ResponseEntity<?> getAllCountriesOrderByTemperature(@RequestParam("order") String order) {
        List<String> countryList = service.getAllCountriesOrderByTemperature(order);
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/diff")
    public ResponseEntity<?> getCountryTemperatureDiff(@RequestBody RegionInformation region) {
        TemperatureDTO tempDiff = service.getRegionTemperatureDifference(region);
        return new ResponseEntity<>(tempDiff, HttpStatus.OK);
    }

    @GetMapping("/by-city")
    public ResponseEntity<?> getCityTempByYear(@RequestBody RegionInformation region) {
        List<TemperatureDTO> tempList = service.getCityTempByYear(region);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }
}
