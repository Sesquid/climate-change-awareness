package com.example.Climate.controllers;

import com.example.Climate.models.City;
import com.example.Climate.models.State;
import com.example.Climate.services.CityService;
import com.example.Climate.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/State")
public class StateController {
    @Autowired
    StateService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCities() {
        List<State> stateList = service.getAllStates();
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getStateByCountryName(@RequestParam("country-name") String countryName) {
        List<State> stateList = service.getStateByCountryName(countryName);
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }
}
