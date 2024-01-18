package com.example.Climate.repositories;

import com.example.Climate.models.Population;
import com.example.Climate.models.RegionInformation;
import com.example.Climate.models.YearRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Integer> {

    @Query("SELECT p FROM Population p WHERE p.countryName = 'World' ")
    List<Population> getWorldPopulation();

    @Query("SELECT p FROM Population p WHERE p.year = :year ORDER BY population_amount DESC")
    List<Population> getAllCountriesPopulationByYear(@Param("year") int year);

    @Query("SELECT p FROM Population p WHERE p.countryName = :countryName")
    List<Population> getPopulationListByCountryName(@Param("countryName") String countryName);

    @Query("SELECT MIN(p.year) AS start, MAX(p.year) AS end FROM Population p")
    Tuple findYearRange();

    @Query("SELECT p.countryName FROM Population p WHERE p.year = 2013 ORDER BY p.population ASC")
    List<String> getAllCountriesOrderByPopulationAsc();

    @Query("SELECT p.countryName FROM Population p WHERE p.year = 2013 ORDER BY p.population DESC")
    List<String> getAllCountriesOrderByPopulationDesc();

    @Query("SELECT p1.population - p2.population FROM Population p1, Population p2 " +
            "WHERE p1.countryName = :#{#region.countryName} AND p1.year = :#{#region.endYear} " +
            "AND p2.countryName = :#{#region.countryName}  AND p2.year = :#{#region.startYear}")
    Long getPopulationDifference(@Param("region") RegionInformation region);

    @Query("SELECT p FROM Population p " +
            "WHERE p.countryName = :#{#region.countryName} " +
            "AND (p.year = :#{#region.endYear} OR p.year = :#{#region.startYear}) ORDER BY p.year DESC ")
    List<Population> getCountryPopulationByYearRange(@Param("region") RegionInformation region);
}
