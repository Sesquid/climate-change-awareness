package com.example.Climate.models;

import javax.persistence.*;

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
    @Column(name = "city_id")
    private int id;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_city_to_country"), referencedColumnName = "country_id")
    private Country country;
    @Column(name = "country_name")
    private String countryName;
    private String latitude;
    private String longtitude;
}
