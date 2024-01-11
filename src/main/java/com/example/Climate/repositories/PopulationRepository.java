package com.example.Climate.repositories;

import com.example.Climate.models.Population;
import com.example.Climate.models.YearRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Integer> {

    @Query("SELECT p From Population p where p.countryName = 'World' ")
    List<Population> getWorldPopulation();

    @Query("SELECT p From Population p where p.year = :year Order by population_amount DESC")
    List<Population> getAllCountriesPopulationByYear(@Param("year") int year);

    @Query("SELECT MIN(p.year) FROM Population p")
    int findMinYear();

    @Query("SELECT MAX(p.year) FROM Population p")
    int findMaxYear();
}
