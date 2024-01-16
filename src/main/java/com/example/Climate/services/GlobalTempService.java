package com.example.Climate.services;

import com.example.Climate.models.GlobalTemperature;
import com.example.Climate.models.GlobalTemperature;
import com.example.Climate.models.YearRange;
import com.example.Climate.repositories.GlobalTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;

@Service
public class GlobalTempService {
    @Autowired
    GlobalTempRepository repo;

    public List<GlobalTemperature> getAllGlobalTemp() {
        return repo.findAll();
    }

    public List<GlobalTemperature> getGlobalTempByYear(int year) {
        return repo.getGlobalTempByYear(year);
    }

    public YearRange findYearRange() {
        Tuple yearRange = repo.findYearRange();
        return new YearRange(yearRange.get("start", Integer.class), yearRange.get("end", Integer.class));
    }
}
