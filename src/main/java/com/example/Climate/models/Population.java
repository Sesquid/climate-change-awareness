package com.example.Climate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "population")
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "countryname")
    private Country country;
    @Column(name = "year")
    private int year;
    @Column(name = "populationInf")
    private Long population;
}
