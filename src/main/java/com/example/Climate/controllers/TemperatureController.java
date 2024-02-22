package com.example.Climate.controllers;

import com.example.Climate.dto.RegionInformation;
import com.example.Climate.dto.SimilarRegion;
import com.example.Climate.dto.TemperatureDTO;
import com.example.Climate.dto.YearRange;
import com.example.Climate.models.Temperature;
import com.example.Climate.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/temperature")
public class TemperatureController {
    @Autowired
    TemperatureService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTemp() {
        return new ResponseEntity<>(service.getAllTemp(), HttpStatus.OK);
    }

    @GetMapping("/get-year-range")
    public ResponseEntity<?> findYearRange() {
        YearRange yearRange = service.findTemperatureYearRange();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }

    @GetMapping("/get-countries/order-by-temperature")
    public ResponseEntity<?> getAllCountriesOrderByTemperature(@RequestParam("order") String order) {
        List<String> countryList = service.getAllCountriesOrderByTemperature(order);
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("region-temperature/diff-by-years")
    public ResponseEntity<?> getCountryTemperatureDiff(@RequestBody RegionInformation region) {
        TemperatureDTO tempDiff = service.getRegionTemperatureDifference(region);
        return new ResponseEntity<>(tempDiff, HttpStatus.OK);
    }

    @GetMapping("/region-temperature/by-year-range")
    public ResponseEntity<?> getRegionTemperatureByYear(@RequestBody RegionInformation region) {
        List<TemperatureDTO> tempList = service.getRegionTemperatureByYearRange(region);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    @PostMapping("/region-temperature-list")
    public ResponseEntity<?> getTemperatureByRegion(@RequestBody RegionInformation region) {
        List<Temperature> tempList = service.getTemperatureListByRegion(region);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    @PostMapping("/region-average-temperature/by-time-period")
    public ResponseEntity<?> getRegionAverageTemperatureInTimePeriod(@RequestBody RegionInformation region) {
        TemperatureDTO avgTemp = service.getRegionAverageTemperatureInTimePeriod(region);
        return new ResponseEntity<>(avgTemp, HttpStatus.OK);
    }

    @GetMapping("/similar-by-temperature")
    public ResponseEntity<?> getRegionSimilarByTemperature(@RequestParam("countryId") Integer countryId,
                                                           @RequestParam("cityId") Optional<Integer> cityId,
                                                           @RequestParam("stateId") Optional<Integer> stateId,
                                                           @RequestParam("startYear") Integer startYear,
                                                           @RequestParam("endYear") Integer endYear,
                                                           @RequestParam("timePeriod") Integer timePeriod,
                                                           @RequestParam("limit") Integer limit) {

        List<SimilarRegion> similarTemperatures = service.getSimilarRegionByTemperature(countryId, stateId,
                cityId, startYear, endYear, timePeriod, limit);

        return new ResponseEntity<>(similarTemperatures, HttpStatus.OK);
    }
}
