package com.example.Climate.services;

import com.example.Climate.exception.QueryDataException;
import com.example.Climate.models.Temp;
import com.example.Climate.repositories.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TempService {
    @Autowired
    TempRepository repo;

    public List<Temp> getAllTemp() {
        List<Temp> tempList = repo.findAll();
        if (tempList.isEmpty()) {
            throw new QueryDataException("All temp list is empty!");
        }
        return tempList;
    }

    public List<Temp> getTempByCountry(@RequestParam String countryName) {
        List<Temp> tempList = repo.getTempByCountry(countryName);
        if(tempList.isEmpty()) {
            throw new QueryDataException("Temp by country list is empty!");
        }
        return tempList;
    }

    public List<Temp> getTempByCity(@RequestParam("countryName") String countryName, @RequestParam("cityName") String cityName){
        List<Temp> tempList = repo.getTempByCountryAndCity(countryName, cityName);
        if(tempList.isEmpty()){
            throw new QueryDataException("Temp by city and country list is empty!");
        }
        return tempList;
    }
    public Object getMinAndMaxYear() {
        Object yearRange = repo.findMinAndMaxYear();
        return yearRange;
    }
}
