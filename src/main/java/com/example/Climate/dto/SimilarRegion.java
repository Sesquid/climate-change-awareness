package com.example.Climate.dto;



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
