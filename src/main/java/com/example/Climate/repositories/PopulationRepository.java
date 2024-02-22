package com.example.Climate.repositories;

import com.example.Climate.models.Country;
import com.example.Climate.models.Population;
import com.example.Climate.dto.RegionInformation;
import com.example.Climate.dto.YearRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Integer> {

    @Query("SELECT p FROM Population p WHERE p.countryName = 'World' ")
    List<Population> getWorldPopulation();

    @Query("SELECT p FROM Population p WHERE p.year = :year ORDER BY population_amount DESC")
    List<Population> getAllCountriesPopulationByYear(@Param("year") int year);

    @Query("SELECT p FROM Population p WHERE p.countryCode = :countryCode")
    List<Population> getPopulationListByCountryCode(@Param("countryCode") String countryCode);

    @Query("SELECT new com.example.Climate.dto.YearRange(MIN(p.year) , MAX(p.year))  FROM Population p")
    YearRange findPopulationYearRange();

    @Query("SELECT p.country FROM Population p WHERE p.year = 2013 ORDER BY p.population ASC")
    List<Country> getAllCountriesOrderBy2013PopulationAsc();

    @Query("SELECT p.country FROM Population p WHERE p.year = 2013 ORDER BY p.population DESC")
    List<Country> getAllCountriesOrderBy2013PopulationDesc();

    @Query("SELECT p1.population - p2.population FROM Population p1, Population p2 " +
            "WHERE p1.countryName = :#{#region.countryName} AND p1.year = :#{#region.endYear} " +
            "AND p2.countryName = :#{#region.countryName}  AND p2.year = :#{#region.startYear}")
    Long findPopulationDifference(@Param("region") RegionInformation region);

    @Query("SELECT p FROM Population p " +
            "WHERE p.countryName = :#{#region.countryName} " +
            "AND (p.year = :#{#region.endYear} OR p.year = :#{#region.startYear}) ORDER BY p.year DESC ")
    List<Population> getCountryPopulationByYearRange(@Param("region") RegionInformation region);
}
