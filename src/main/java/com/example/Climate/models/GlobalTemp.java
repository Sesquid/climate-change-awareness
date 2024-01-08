package com.example.Climate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "globaltemp")
public class GlobalTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "year")
    private int year;
    @Column(name = "avgtemp")
    private Float avgTemp;
    @Column(name = "mintemp")
    private Float minTemp;
    @Column(name = "maxtemp")
    private Float maxTemp;
    @Column(name = "landoceanavgtemp")
    private Float landOceanAvgTemp;
    @Column(name = "landoceanmintemp")
    private Float landOceanMinTemp;
    @Column(name = "landoceanmaxtemp")
    private Float landOceanMaxTemp;
}
