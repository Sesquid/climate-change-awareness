package com.example.Climate.repositories;

import com.example.Climate.dto.CountryDTO;
import com.example.Climate.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.sql.ResultSet;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT COUNT(*) FROM Country")
    int getNumberOfCountries();

    @Query(value = """
            SELECT DISTINCT c
            FROM Country c
            ORDER BY c.countryName ASC
            """)
    List<Country> getCountryOrderByNameASC();

    @Query(value = """
            SELECT DISTINCT c
            FROM Country c
            ORDER BY c.countryName DESC
            """)
    List<Country> getCountryOrderByNameDESC();

    @Query(value = """
            SELECT DISTINCT c, p.population
            FROM Country c
            JOIN Population p ON c.id = p.country.id
            WHERE p.year = 2013
            ORDER BY p.population ASC
            """)
    List<Country> getCountryOrderByPopulationASC();

    @Query(value = """
            SELECT DISTINCT c, p.population
            FROM Country c
            JOIN Population p ON c.id = p.country.id
            WHERE p.year = 2013
            ORDER BY p.population DESC
            """)
    List<Country> getCountryOrderByPopulationDESC();
//
    @Query(value = """
            SELECT DISTINCT c, t.avgTemp
            FROM Country c
            JOIN Temperature t ON c.id = t.country.id
            WHERE t.year = 2013
            AND t.city.id IS NULL
            AND t.state.id IS NULL
            ORDER BY t.avgTemp ASC
            """)
    List<Country> getCountryOrderByTemperatureASC();
    @Query(value = """
            SELECT DISTINCT c, t.avgTemp
            FROM Country c
            JOIN Temperature t ON c.id = t.country.id
            WHERE t.year = 2013
            AND t.city.id IS NULL
            AND t.state.id IS NULL
            ORDER BY t.avgTemp DESC
            """)
    List<Country> getCountryOrderByTemperatureDESC();

}
