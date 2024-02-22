package com.example.Climate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionInformation {
    String countryName;
    String regionType;
    String regionName;
    String latitude;
    String longtitude;
    int startYear;
    int endYear;

    public RegionInformation(String countryName, int startYear, int endYear) {
        this.countryName = countryName;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public RegionInformation(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public RegionInformation(String countryName, String regionType, String regionName, int startYear, int endYear) {
        this.countryName = countryName;
        this.regionType = regionType;
        this.regionName = regionName;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
