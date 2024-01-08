package com.example.Climate.controllers;

import com.example.Climate.services.GlobalTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/globalTemp")
public class GlobalTempController {
    @Autowired
    GlobalTempService service;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllGlobalTemp() {

        try{
            return new ResponseEntity<>(service.getAllGlobalTemp(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getGlobalTempByYear(@RequestParam int year) {
        return new ResponseEntity<>(service.getGlobalTempByYear(year), HttpStatus.OK);
    }
}
