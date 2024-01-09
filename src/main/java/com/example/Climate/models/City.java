package com.example.Climate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cityname")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "countryid", foreignKey = @ForeignKey(name = "fk_city_to_country"), referencedColumnName = "id")
    private Country country;
    @Column(name = "countryname")
    private String countryName;
    private String latitude;
    private String longtitude;
}
