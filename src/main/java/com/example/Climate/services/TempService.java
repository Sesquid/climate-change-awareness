package com.example.Climate.services;

import com.example.Climate.models.Temp;
import com.example.Climate.repositories.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempService {
    @Autowired
    TempRepository repo;

    public List<Temp> getAllTemp() {
        return repo.findAll();
    }
}
