package com.example.Climate.controllers;

import com.example.Climate.services.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/temp")
public class TempController {
    @Autowired
    TempService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTemp() {
        return new ResponseEntity<>(service.getAllTemp(), HttpStatus.OK);
    }
    @GetMapping("/year-range")
    public ResponseEntity<?> getMinAndMaxYear() {
        Object yearRange = service.getMinAndMaxYear();
        return new ResponseEntity<>(yearRange, HttpStatus.OK);
    }
}
