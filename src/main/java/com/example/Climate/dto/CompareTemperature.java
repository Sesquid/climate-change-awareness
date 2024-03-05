package com.example.Climate.dto;

public interface CompareTemperature {
    Double getEndYearAvgTemperature();
    Double getEndYearMinTemperature();
    Double getEndYearMaxTemperature();
    Double getStartYearAvgTemperature();
    Double getStartYearMinTemperature();
    Double getStartYearMaxTemperature();
    Double getAvgTemperatureDifference();
    Double getMinTemperatureDifference();
    Double getMaxTemperatureDifference();
}
