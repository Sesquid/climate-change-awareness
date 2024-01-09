package com.example.Climate.controllers;

import com.example.Climate.exception.QueryDataException;
import com.example.Climate.models.Country;
import com.example.Climate.repositories.CountryRepository;
import com.example.Climate.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Country")
public class CountryController {
    @Autowired
    CountryService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCountries() {
        List<Country> countryList = service.getAllCountries();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }
}
