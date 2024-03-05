package com.example.Climate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionInformation {
    int countryId;
    int cityId;
    int stateId;
    int startYear;
    int endYear;

}
