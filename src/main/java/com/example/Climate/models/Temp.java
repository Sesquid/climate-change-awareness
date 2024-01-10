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
    @Column(name = "id")
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
    @JoinColumn(name = "cityid", foreignKey = @ForeignKey(name = "fk_temp_to_city"), referencedColumnName = "id")
    private City city;
    @Column(name = "cityname")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "countryid", foreignKey = @ForeignKey(name = "fk_temp_to_country"), referencedColumnName = "id")
    private Country country;
    @Column(name = "countryname")
    private String countryName;
    @ManyToOne
    @JoinColumn(name = "stateid", foreignKey = @ForeignKey(name = "fk_temp_to_state"), referencedColumnName = "id")
    private State state;
    @Column(name = "statename")
    private String stateName;
    private String latitude;
    private String longtitude;
}
