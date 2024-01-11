package com.example.Climate.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "global_temperature")
public class GlobalTemperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "global_temperature_id")
    private Integer id;
    @Column(name = "year")
    private int year;
    @Column(name = "average_temperature")
    private Float avgTemp;
    @Column(name = "min_temperature")
    private Float minTemp;
    @Column(name = "max_temperature")
    private Float maxTemp;
    @Column(name = "land_ocean_average_temperature")
    private Float landOceanAvgTemp;
    @Column(name = "land_ocean_min_temperature")
    private Float landOceanMinTemp;
    @Column(name = "land_ocean_max_temperature")
    private Float landOceanMaxTemp;
}
