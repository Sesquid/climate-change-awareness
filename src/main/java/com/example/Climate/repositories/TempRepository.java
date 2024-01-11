package com.example.Climate.repositories;

import com.example.Climate.models.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempRepository extends JpaRepository<Temperature, Integer> {

    @Query("SELECT t from Temperature t where t.countryName = :countryName")
    List<Temperature> getTempByCountry(@Param("countryName") String countryName);

    @Query("Select t from Temperature t where t.countryName = :countryName and t.cityName = :cityName")
    List<Temperature> getTempByCountryAndCity(@Param("countryName") String countryName, @Param("cityName") String cityName);

    @Query("SELECT MIN(t.year) FROM Temperature t")
    int findMinYear();

    @Query("SELECT MAX(t.year) FROM Temperature t")
    int findMaxYear();
}
