package com.example.Climate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cityname")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "countryname", referencedColumnName = "countryname")
    private Country country;
    private String latitude;
    private String longtitude;
}
