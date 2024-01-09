package com.example.Climate.controllers;

import com.example.Climate.models.GlobalTemp;
import com.example.Climate.services.GlobalTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/globalTemp")
public class GlobalTempController {

    @Autowired
    GlobalTempService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllGlobalTemp() {
        List<GlobalTemp> globalTempList = service.getAllGlobalTemp();
        return new ResponseEntity<>(globalTempList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getGlobalTempByYear(@RequestParam int year) {
        List<GlobalTemp> globalTempList = service.getGlobalTempByYear(year);
        return new ResponseEntity<>(globalTempList, HttpStatus.OK);
    }
}
