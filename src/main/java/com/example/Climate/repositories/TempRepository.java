package com.example.Climate.repositories;

import com.example.Climate.models.Temp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempRepository extends JpaRepository<Temp, Integer> {

    @Query("SELECT t from Temp t where t.countryName = :countryName")
    List<Temp> getTempByCountry(@Param("countryName") String countryName);

    @Query("Select t from Temp t where t.countryName = :countryName and t.cityName = :cityName")
    List<Temp> getTempByCountryAndCity(@Param("countryName") String countryName, @Param("cityName") String cityName);

    @Query("SELECT MIN(t.year) as minYear, MAX(t.year) as maxYear FROM Temp t")
    Object findMinAndMaxYear();
}
