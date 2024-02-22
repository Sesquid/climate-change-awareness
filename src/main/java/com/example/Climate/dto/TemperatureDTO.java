package com.example.Climate.dto;


import com.example.Climate.models.Temperature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDTO {
    Double avgTemp;
    Double minTemp;
    Double maxTemp;

    public TemperatureDTO(Temperature temperature) {
        this.avgTemp = temperature.getAvgTemp();
        this.minTemp = temperature.getMinTemp();
        this.maxTemp = temperature.getMaxTemp();
    }
}