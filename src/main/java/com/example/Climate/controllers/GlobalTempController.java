package com.example.Climate.controllers;


import com.example.Climate.DTO.TemperatureDTO;
import com.example.Climate.models.*;
import com.example.Climate.models.GlobalTemperature;
import com.example.Climate.services.GlobalTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/global-temp")
public class GlobalTempController {

    @Autowired
    GlobalTempService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllGlobalTemp() {
        List<GlobalTemperature> globalTempList = service.getAllGlobalTemp();
        return new ResponseEntity<>(globalTempList, HttpStatus.OK);
    }

    @GetMapping("/by-year-range")
    public ResponseEntity<?> getGlobalTempByYearRange(@RequestBody RegionInformation region) {
        List<GlobalTemperature> globalTempList = service.getGlobalTempByYearRange(region);
        return new ResponseEntity<>(globalTempList, HttpStatus.OK);
    }

    @GetMapping("/year-range")
    public ResponseEntity<?> findYearRange() {
        YearRange yearRange = service.findYearRange();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }

    @GetMapping("/diff")
    public ResponseEntity<?> getWorldTemperatureDiff(@RequestBody RegionInformation region) {
        TemperatureDTO tempDiff = service.getTemperatureDifference(region);
        return new ResponseEntity<>(tempDiff, HttpStatus.OK);
    }
}
