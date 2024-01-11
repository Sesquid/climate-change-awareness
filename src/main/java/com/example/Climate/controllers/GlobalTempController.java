package com.example.Climate.controllers;

import com.example.Climate.models.GlobalTemperature;
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

    @GetMapping("/")
    public ResponseEntity<?> getGlobalTempByYear(@RequestParam int year) {
        List<GlobalTemperature> globalTempList = service.getGlobalTempByYear(year);
        return new ResponseEntity<>(globalTempList, HttpStatus.OK);
    }

    @GetMapping("/year-range")
    public ResponseEntity<?> getMinAndMaxYear() {
        Object yearRange = service.getMinAndMaxYear();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }
}
