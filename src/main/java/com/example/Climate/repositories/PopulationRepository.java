package com.example.Climate.repositories;

import com.example.Climate.models.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Integer> {

    @Query("SELECT p From Population p where p.countryName = 'World' ")
    List<Population> getWorldPopulation();

    @Query("SELECT p From Population p where p.year = :year and p.countryName != 'World' ")
    List<Population> getAllCountriesPopulationByYear(@Param("year") int year);

    @Query("SELECT MIN(p.year) as minYear, MAX(p.year) as maxYear FROM Population p")
    Object findMinAndMaxYear();
}
