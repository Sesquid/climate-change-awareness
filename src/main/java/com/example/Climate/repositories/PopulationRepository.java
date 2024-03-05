package com.example.Climate.repositories;

import com.example.Climate.dto.ComparePopulation;
import com.example.Climate.dto.YearRange;
import com.example.Climate.models.Country;
import com.example.Climate.models.Population;
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

    @Query("""
            SELECT
            p1.population AS endYearPopulation,
            p2.population AS startYearPopulation,
            p1.population - p2.population AS populationDifference
            FROM Population p1, Population p2
            WHERE p1.countryCode = :countryCode AND p1.year = :endYear
            AND p2.countryCode = :countryCode  AND p2.year = :startYear
            """)
    ComparePopulation findPopulationDifference(@Param("countryCode") String countryCode,
                                               @Param("startYear") int startYear,
                                               @Param("endYear") int endYear);

//    @Query("""
//            SELECT p FROM Population p
//            WHERE p.countryCode = :countryCode
//            AND (p.year = :endYear OR p.year = :startYear)
//            ORDER BY p.year DESC
//            """)
//    List<Population> getCountryPopulationByYearRange(@Param("countryCode") String countryCode,
//                                                     @Param("startYear") int startYear,
//                                                     @Param("endYear") int endYear);
}
