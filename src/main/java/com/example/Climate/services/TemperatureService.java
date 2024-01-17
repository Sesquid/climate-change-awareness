package com.example.Climate.services;

import com.example.Climate.DTO.TemperatureDTO;
import com.example.Climate.exception.NoDataException;
import com.example.Climate.models.RegionInformation;
import com.example.Climate.models.Temperature;
import com.example.Climate.models.YearRange;
import com.example.Climate.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureService {
    @Autowired
    TemperatureRepository repo;

    public List<Temperature> getAllTemp() {
        return repo.findAll();
    }

    public List<Temperature> getTempByCountry(String countryName) {
        return repo.getTempByCountry(countryName);
    }

    public List<TemperatureDTO> getCityTempByYear(RegionInformation region) {
        List<Temperature> tempList;
        tempList = region.getRegion().equals("city") ? repo.getCityTempByYear(region): repo.getStateTempByYear(region);
        List<TemperatureDTO> tempDTOList = new ArrayList<>();
        for (Temperature temp : tempList) {
            tempDTOList.add(new TemperatureDTO(temp));
        }
        return tempDTOList;
    }

    public YearRange findYearRange() {
        Tuple yearRange = repo.findYearRange();
        return new YearRange(yearRange.get("start", Integer.class), yearRange.get("end", Integer.class));
    }

    public List<String> getAllCountriesOrderByTemperature(String order) {
        return order.equals("ASC") ? repo.getAllCountriesOrderByTemperatureAsc() : repo.getAllCountriesOrderByTemperatureDesc();
    }

    public TemperatureDTO getRegionTemperatureDifference(RegionInformation region) {
        Tuple tempDiff;
        tempDiff = region.getRegion().isEmpty() ? repo.getCountryTemperatureDiff(region)
                : region.getRegion().equals("state") ? repo.getStateTemperatureDiff(region)
                : repo.getCityTemperatureDiff(region);
        if (tempDiff == null) {
            throw new NoDataException("Data provided not valid!");
        }
        return new TemperatureDTO((Float) tempDiff.get("avgTemp"),
                (Float) tempDiff.get("minTemp"),
                (Float) tempDiff.get("maxTemp"));
    }

}
