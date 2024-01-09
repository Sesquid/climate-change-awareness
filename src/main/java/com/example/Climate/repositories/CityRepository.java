package com.example.Climate.repositories;

import com.example.Climate.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c WHERE c.countryName = :countryName")
    List<City> getCityByCountryName(@Param("countryName") String countryName);
}
