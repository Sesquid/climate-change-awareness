package com.example.Climate.services;

import com.example.Climate.dto.RegionInformation;
import com.example.Climate.dto.SimilarRegion;
import com.example.Climate.dto.TemperatureDTO;
import com.example.Climate.dto.YearRange;
import com.example.Climate.exception.NoDataException;
import com.example.Climate.models.Temperature;
import com.example.Climate.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureService {
    @Autowired
    TemperatureRepository repo;

    public List<Temperature> getAllTemp() {
        return repo.findAll();
    }

    public List<TemperatureDTO> getRegionTemperatureByYearRange(RegionInformation region) {
        List<Temperature> tempList;
        tempList = region.getRegionType().equalsIgnoreCase("country") ? repo.getCountryTemperatureByYearRange(region)
                : region.getRegionType().equalsIgnoreCase("city") ? repo.getCityTemperatureByYearRange(region)
                : repo.getStateTemperatureByYearRange(region);
        List<TemperatureDTO> tempDTOList = new ArrayList<>();
        for (Temperature temp : tempList) {
            tempDTOList.add(new TemperatureDTO(temp));
        }
        return tempList.size() == 2 ? tempDTOList : new ArrayList<>();
    }

    public YearRange findTemperatureYearRange() {
        return repo.findTemperatureYearRange();
    }

    public List<String> getAllCountriesOrderByTemperature(String order) {
        return order.equalsIgnoreCase("ASC") ? repo.getAllCountriesOrderByTemperatureAsc() : repo.getAllCountriesOrderByTemperatureDesc();
    }

    public TemperatureDTO getRegionTemperatureDifference(RegionInformation region) {
        Tuple temperatureDiff;
        if (region.getRegionType().equalsIgnoreCase("country"))
            temperatureDiff = repo.getCountryTemperatureDiff(region);
        else if (region.getRegionType().equalsIgnoreCase("state"))
            temperatureDiff = repo.getStateTemperatureDiff(region);
        else temperatureDiff = repo.getCityTemperatureDiff(region);

        if (temperatureDiff == null) {
            throw new NoDataException("Data provided not valid!");
        }
        return new TemperatureDTO((Double) temperatureDiff.get(0),
                (Double) temperatureDiff.get(1),
                (Double) temperatureDiff.get(2));
    }

    public List<Temperature> getTemperatureListByRegion(RegionInformation region) {
        List<Temperature> tempList;

        if (region.getRegionType().equalsIgnoreCase("country")) {
            tempList = repo.getTemperatureListByCountry(region);
        } else if (region.getRegionType().equalsIgnoreCase("city")) {
            tempList = repo.getTemperatureListByCity(region);
        } else if (region.getRegionType().equalsIgnoreCase("state")) {
            tempList = repo.getTemperatureListByState(region);
        } else {
            tempList = new ArrayList<>();
        }

        return tempList;
    }


    public TemperatureDTO getRegionAverageTemperatureInTimePeriod(RegionInformation region) {
        Tuple avgTemp;
        if (region.getRegionType().equalsIgnoreCase("country")) {
            avgTemp = repo.getCountryAverageTemperatureInTimePeriod(region);
        } else if (region.getRegionType().equalsIgnoreCase("city")) {
            avgTemp = repo.getCityAverageTemperatureInTimePeriod(region);
        } else {
            avgTemp = repo.getStateAverageTemperatureInTimePeriod(region);
        }
        if (avgTemp != null)
            return new TemperatureDTO((Double) avgTemp.get(0),
                    (Double) avgTemp.get(1),
                    (Double) avgTemp.get(2));
        return null;
    }

    public List<SimilarRegion> getSimilarRegionByTemperature(int countryId, Optional<Integer> stateId, Optional<Integer> cityId, int startYear, int endYear, int timePeriod, int limit) {
        List<SimilarRegion> similarRegions = new ArrayList<>();
        if (stateId.isEmpty() && cityId.isEmpty()) {
            similarRegions = repo.getSimilarRegionByCountryTemperature(countryId, startYear, endYear, timePeriod, limit);
        } else if (stateId.isEmpty()) {
            similarRegions = repo.getSimilarRegionByCountryCityTemperature(countryId, cityId.get(), startYear, endYear, timePeriod, limit);
        } else if (cityId.isEmpty()) {
            similarRegions = repo.getSimilarRegionByCountryStateTemperature(countryId, stateId.get(), startYear, endYear, timePeriod, limit);
        }
        return similarRegions;
    }
}