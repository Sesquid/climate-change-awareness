package com.example.Climate.services;

import com.example.Climate.models.GlobalTemp;
import com.example.Climate.repositories.GlobalTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalTempService {
    @Autowired
    GlobalTempRepository repo;

    public List<GlobalTemp> getAllGlobalTemp() {
        return repo.findAll();
    }

    public List<GlobalTemp> getGlobalTempByYear(int year) {
        return repo.getGlobalTempByYear(year);
    }
}
