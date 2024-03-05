package com.example.Climate.dto;


import com.example.Climate.models.Temperature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface TemperatureDTO {
    Double getAvgTemp();
    Double getMinTemp();
    Double getMaxTemp();

}