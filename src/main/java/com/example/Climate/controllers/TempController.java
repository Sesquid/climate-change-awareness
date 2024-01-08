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
@RequestMapping("/api/Temp")
public class TempController {
    @Autowired
    TempService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTemp() {
        return new ResponseEntity<>(service.getAllTemp(), HttpStatus.OK);
    }
}
