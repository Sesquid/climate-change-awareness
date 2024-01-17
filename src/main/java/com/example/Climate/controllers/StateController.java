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
@RequestMapping("/api/state")
public class StateController {
    @Autowired
    StateService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCities() {
        List<State> stateList = service.getAllStates();
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getStateByCountryName(@RequestParam("countryName") String countryName) {
        List<State> stateList = service.getStateByCountryName(countryName);
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    @GetMapping("/number-of-states")
    public ResponseEntity<?> getNumberOfStates() {
        int numberOfStates = service.getNumberOfStates();
        return new ResponseEntity<>(numberOfStates, HttpStatus.OK);
    }
}
