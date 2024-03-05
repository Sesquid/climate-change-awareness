package com.example.Climate.services;

import com.example.Climate.dto.CompareTemperature;
import com.example.Climate.dto.SimilarRegion;
import com.example.Climate.dto.TemperatureDTO;
import com.example.Climate.dto.YearRange;
import com.example.Climate.exception.NoDataException;
import com.example.Climate.models.Temperature;
import com.example.Climate.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureService {
    @Autowired
    TemperatureRepository repo;

//    public List<Temperature> getRegionTemperatureByYearRange(int countryId,
//                                                                Optional<Integer> stateId,
//                                                                Optional<Integer> cityId,
//                                                                int startYear,
//                                                                int endYear) {
//        List<Temperature> tempList;
//
//        if (stateId.isPresent()) {
//            tempList = repo.getStateTemperatureByYearRange(countryId, stateId.get(), startYear, endYear);
//        } else if (cityId.isPresent()) {
//            tempList = repo.getCityTemperatureByYearRange(countryId, cityId.get(), startYear, endYear);
//        } else tempList = repo.getCountryTemperatureByYearRange(countryId, startYear, endYear);
//        return tempList.size() == 2 ? tempList : new ArrayList<>();
//    }

    public YearRange findTemperatureYearRange() {
        return repo.findTemperatureYearRange();
    }

    public CompareTemperature getRegionTemperatureDifference(int countryId,
                                                             Optional<Integer> stateId,
                                                             Optional<Integer> cityId,
                                                             int startYear,
                                                             int endYear) {
        CompareTemperature temperatureDiff;

        if (stateId.isPresent()) {
            temperatureDiff = repo.getStateTemperatureDiff(countryId, stateId.get(), startYear, endYear);
        } else if (cityId.isPresent()) {
            temperatureDiff = repo.getCityTemperatureDiff(countryId, cityId.get(), startYear, endYear);
        } else temperatureDiff = repo.getCountryTemperatureDiff(countryId, startYear, endYear);

        return temperatureDiff;
    }

    public List<Temperature> getTemperatureListByRegion(int countryId,
                                                        Optional<Integer> stateId,
                                                        Optional<Integer> cityId) {
        List<Temperature> tempList;

        if (stateId.isPresent()) {
            tempList = repo.getTemperatureListByState(countryId, stateId.get());
        } else if (cityId.isPresent()) {
            tempList = repo.getTemperatureListByCity(countryId, cityId.get());
        } else tempList = repo.getTemperatureListByCountry(countryId);

        return tempList;
    }


    public TemperatureDTO getRegionAverageTemperatureInTimePeriod(int countryId,
                                                                  Optional<Integer> stateId,
                                                                  Optional<Integer> cityId,
                                                                  int startYear,
                                                                  int timePeriod) {
        TemperatureDTO avgTemp;
        if (stateId.isPresent()) {
            avgTemp = repo.getStateAverageTemperatureInTimePeriod(countryId, stateId.get(), startYear, timePeriod);
        } else if (cityId.isPresent()) {
            avgTemp = repo.getCityAverageTemperatureInTimePeriod(countryId, cityId.get(), startYear, timePeriod);
        } else avgTemp = repo.getCountryAverageTemperatureInTimePeriod(countryId, startYear, timePeriod);

        return avgTemp;
    }

    public List<SimilarRegion> getSimilarRegionByTemperature(int countryId, Optional<Integer> stateId, Optional<Integer> cityId, int startYear, int timePeriod, int limit) {
        List<SimilarRegion> similarRegions = new ArrayList<>();
        if (stateId.isEmpty() && cityId.isEmpty()) {
            similarRegions = repo.getSimilarRegionByCountryTemperature(countryId, startYear, timePeriod, limit);
        } else if (stateId.isEmpty()) {
            similarRegions = repo.getSimilarRegionByCountryCityTemperature(countryId, cityId.get(), startYear, timePeriod, limit);
        } else if (cityId.isEmpty()) {
            similarRegions = repo.getSimilarRegionByCountryStateTemperature(countryId, stateId.get(), startYear, timePeriod, limit);
        }
        return similarRegions;
    }
}