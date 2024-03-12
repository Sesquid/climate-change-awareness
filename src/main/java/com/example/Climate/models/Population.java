package com.example.Climate.models;

import javax.persistence.*;

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
    @Column(name = "population_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_population_to_country"), referencedColumnName = "country_id")
    private Country country;
//    @Column(name = "country_name")
//    private String countryName;
//    @Column(name = "country_code")
//    private String countryCode;
    @Column(name = "year")
    private int year;
    @Column(name = "population_amount")
    private Long population;
}
