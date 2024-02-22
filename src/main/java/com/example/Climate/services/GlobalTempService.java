package com.example.Climate.services;

import com.example.Climate.dto.TemperatureDTO;
import com.example.Climate.models.GlobalTemperature;
import com.example.Climate.dto.YearRange;
import com.example.Climate.repositories.GlobalTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalTempService {
    @Autowired
    GlobalTempRepository repo;

    public List<GlobalTemperature> getAllGlobalTemp() {
        return repo.findAll();
    }

    public List<GlobalTemperature> getGlobalTempByYearRange(YearRange yearRange) {
        return repo.getGlobalTemperatureByYearRange(yearRange);
    }

    public YearRange findYearRange() {
        return repo.findGlobalTemperatureYearRange();
    }

    public TemperatureDTO getTemperatureDifference(YearRange yearRange) {
        return repo.findWorldTemperatureDifference(yearRange);
    }
}
