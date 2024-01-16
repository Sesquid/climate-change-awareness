package com.example.Climate.repositories;

import com.example.Climate.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT COUNT(*) FROM Country")
    int getNumberOfCountries();

    @Query("SELECT c.countryName FROM Country c ORDER BY c.countryName ASC")
    List<String> getAllCountriesOrderByNameAsc();

    @Query("SELECT c.countryName FROM Country c ORDER BY c.countryName DESC")
    List<String> getAllCountriesOrderByNameDesc();
}
