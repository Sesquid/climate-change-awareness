package com.example.Climate.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "temperature")
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temperature_id")
    private int id;
    @Column(name = "year")
    private int year;
    @Column(name = "average_temperature")
    private Float avgTemp;
    @Column(name = "min_temperature")
    private Float minTemp;
    @Column(name = "max_temperature")
    private Float maxTemp;
    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_temp_to_city"), referencedColumnName = "city_id")
    private City city;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_temp_to_country"), referencedColumnName = "country_id")
    private Country country;
    @Column(name = "country_name")
    private String countryName;
    @ManyToOne
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_temp_to_state"), referencedColumnName = "state_id")
    private State state;
    @Column(name = "state_name")
    private String stateName;
    private String latitude;
    private String longtitude;

    public Temperature(Float avgTemp, Float minTemp, Float maxTemp) {
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}
