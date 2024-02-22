package com.example.Climate.dto;

import com.example.Climate.models.City;
import com.example.Climate.models.State;


public interface SimilarRegion {
    Integer getPeriodStartYear();

    Integer getPeriodEndYear();

    Integer getCountry();

    Integer getCity();

    Integer getState();

    Double getAverageTemperature();

    Double getMaxTemperature();

    Double getMinTemperature();

    Double getTemperatureDifference();


}
