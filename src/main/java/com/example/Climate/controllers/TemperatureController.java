package com.example.Climate.controllers;

import com.example.Climate.dto.CompareTemperature;
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

//    @GetMapping("/get-all")
//    public ResponseEntity<?> getAllTemp() {
//        return new ResponseEntity<>(service.getAllTemp(), HttpStatus.OK);
//    }

    @GetMapping("/get-year-range")
    public ResponseEntity<?> findYearRange() {
        YearRange yearRange = service.findTemperatureYearRange();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }

    @GetMapping("/region-temperature/diff-by-years")
    public ResponseEntity<?> getRegionTemperatureDiff(@RequestParam("countryId") Integer countryId,
                                                      @RequestParam("cityId") Optional<Integer> cityId,
                                                      @RequestParam("stateId") Optional<Integer> stateId,
                                                      @RequestParam("startYear") Integer startYear,
                                                      @RequestParam("endYear") Integer endYear) {
        CompareTemperature tempDiff = service.getRegionTemperatureDifference(countryId, stateId, cityId, startYear, endYear);
        return new ResponseEntity<>(tempDiff, HttpStatus.OK);
    }

    //    @GetMapping("/region-temperature/by-year-range")
//    public ResponseEntity<?> getRegionTemperatureByYear(@RequestBody RegionInformation region) {
//        List<TemperatureDTO> tempList = service.getRegionTemperatureByYearRange(region);
//        return new ResponseEntity<>(tempList, HttpStatus.OK);
//    }

    @GetMapping("/region-temperature-list")
    public ResponseEntity<?> getRegionTemperatureList(@RequestParam("countryId") Integer countryId,
                                                      @RequestParam("cityId") Optional<Integer> cityId,
                                                      @RequestParam("stateId") Optional<Integer> stateId) {
        List<Temperature> tempList = service.getTemperatureListByRegion(countryId, stateId, cityId);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    //
    @GetMapping("/region-average-temperature/by-time-period")
    public ResponseEntity<?> getRegionAverageTemperatureInTimePeriod(@RequestParam("countryId") Integer countryId,
                                                                     @RequestParam("cityId") Optional<Integer> cityId,
                                                                     @RequestParam("stateId") Optional<Integer> stateId,
                                                                     @RequestParam("startYear") Integer startYear,
                                                                     @RequestParam("timePeriod") Integer timePeriod) {
        TemperatureDTO avgTemp = service.getRegionAverageTemperatureInTimePeriod(countryId, stateId, cityId, startYear, timePeriod);
        return new ResponseEntity<>(avgTemp, HttpStatus.OK);
    }

    @GetMapping("/similar-region")
    public ResponseEntity<?> getRegionBySimilarTemperature(@RequestParam("countryId") Integer countryId,
                                                           @RequestParam("cityId") Optional<Integer> cityId,
                                                           @RequestParam("stateId") Optional<Integer> stateId,
                                                           @RequestParam("startYear") Integer startYear,
                                                           @RequestParam("timePeriod") Integer timePeriod,
                                                           @RequestParam("limit") Integer limit) {

        List<SimilarRegion> similarRegions = service.getSimilarRegionByTemperature(countryId, stateId,
                cityId, startYear, timePeriod, limit);

        return new ResponseEntity<>(similarRegions, HttpStatus.OK);
    }
}
