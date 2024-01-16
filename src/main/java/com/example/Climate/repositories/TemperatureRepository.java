package com.example.Climate.repositories;

import com.example.Climate.models.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {

    @Query("SELECT t FROM Temperature t WHERE t.countryName = :countryName AND t.stateName IS NULL AND t.cityName IS NULL")
    List<Temperature> getTempByCountry(@Param("countryName") String countryName);

    @Query("Select t FROM Temperature t WHERE t.countryName = :countryName AND t.cityName = :cityName")
    List<Temperature> getTempByCountryAndCity(@Param("countryName") String countryName, @Param("cityName") String cityName);

    @Query("SELECT MIN(t.year) AS start, MAX(t.year) AS end FROM Temperature t")
    Tuple findYearRange();

    @Query("SELECT t.countryName FROM Temperature t WHERE t.year = 2013 AND t.stateName IS NULL AND t.cityName IS NULL ORDER BY t.avgTemp ASC")
    List<String> getAllCountriesOrderByTemperatureAsc();

    @Query("SELECT t.countryName FROM Temperature t WHERE t.year = 2013 AND t.stateName IS NULL AND t.cityName IS NULL ORDER BY t.avgTemp DESC")
    List<String> getAllCountriesOrderByTemperatureDesc();
}
