package com.example.Climate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "temp")
public class Temp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "year")
    private int year;
    @Column(name = "avgtemp")
    private Float avgTemp;
    @Column(name = "mintemp")
    private Float minTemp;
    @Column(name = "maxtemp")
    private Float maxTemp;
    @ManyToOne
    @JoinColumn(name = "cityname", referencedColumnName = "cityname")
    private City city;
    @ManyToOne
    @JoinColumn(name = "countryname", referencedColumnName = "countryname")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "statename", referencedColumnName = "statename")
    private State state;
}
